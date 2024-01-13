package com.example.demo.dao.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.model.Customer;
@Repository("customerDAO")
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static final Logger log = LoggerFactory.getLogger(CustomerDAOImpl.class);
	private static final String ALL_ROWS="SELECT ID, FNAME, LNAME, ADDRESS, CITY, STATE, ZIP_CD, PHONE_NO FROM CUSTOMER";
	private static final String SAVE="INSERT INTO CUSTOMER(ID, FNAME, LNAME, ADDRESS, CITY, STATE, ZIP_CD, PHONE_NO) VALUES (:ID, :FNAME, :LNAME, :ADDRESS, :CITY, :STATE, :ZIP_CD, :PHONE_NO)";
	private static final String UPDATE_SQL="UPDATE CUSTOMER SET ID=:ID, FNAME=:FNAME, LNAME=:LNAME, ADDRESS=:ADDRESS, CITY=:CITY, STATE = :STATE, ZIP_CD=:ZIP_CD, PHONE_NO=:PHONE_NO WHERE ID=:ID";
	private static final String GET_SQL="SELECT ID, FNAME, LNAME, ADDRESS, CITY, STATE, ZIP_CD, PHONE_NO FROM CUSTOMER WHERE ID=:ID";
	private static final String DELETE_SQL="DELETE FROM CUSTOMER WHERE ID=:ID";
	@Override
	public List<Customer> list () {
				return  namedParameterJdbcTemplate.query(ALL_ROWS, new BeanPropertyRowMapper<Customer>(Customer.class));
	}

	@Override
	public int save(Customer customer) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("FNAME", customer.getfName()).addValue("LNAME", customer.getlName())
				.addValue("ID", customer.getId()).addValue("FNAME", customer.getfName())
				.addValue("FNAME", customer.getfName()).addValue("ADDRESS",customer.getAddress())
				.addValue("CITY", customer.getCity()).addValue("STATE", customer.getState())
				.addValue("ZIP_CD", customer.getZip_cd()).addValue("FNAME", customer.getfName())
				.addValue("PHONE_NO", customer.getPhone_no());
			return namedParameterJdbcTemplate.update(SAVE, namedParameters);
	}

	@Override
	public Optional<Customer> get(int id) {
		Customer customer=null;
		SqlParameterSource namedParameters = new MapSqlParameterSource("ID", id);
		try {
			customer = namedParameterJdbcTemplate.queryForObject(GET_SQL, namedParameters, new BeanPropertyRowMapper<Customer>(Customer.class));
		} catch (DataAccessException e) {
			log.info("Customer not found: "+id);
		}
		return Optional.ofNullable(customer);
	}

	@Override
	public int update(Customer customer, int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("FNAME", customer.getfName()).addValue("LNAME", customer.getlName())
				.addValue("ID", customer.getId()).addValue("FNAME", customer.getfName())
				.addValue("FNAME", customer.getfName()).addValue("ADDRESS",customer.getAddress())
				.addValue("CITY", customer.getCity()).addValue("STATE", customer.getState())
				.addValue("ZIP_CD", customer.getZip_cd()).addValue("FNAME", customer.getfName())
				.addValue("PHONE_NO", customer.getPhone_no());
			return namedParameterJdbcTemplate.update(SAVE, namedParameters);
		
	}

	@Override
	public int delete(int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("ID", id);
		return namedParameterJdbcTemplate.update(DELETE_SQL, namedParameters);
	}

}
