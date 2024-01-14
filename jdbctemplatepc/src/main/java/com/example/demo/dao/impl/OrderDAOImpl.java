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

import com.example.demo.dao.OrderDAO;
import com.example.demo.model.Orders;

@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO {
	
	@Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static final Logger log = LoggerFactory.getLogger(OrderDAOImpl.class);
	private static final String GET="SELECT ID, CUST_ID, ORDER_DATE FROM ORDERS WHERE ID=:ID";
	private static final String SAVE="INSERT INTO ORDERS(ID, CUST_ID, ORDER_DATE) VALUES (:ID, :CUST_ID, :ORDER_DATE)";
	private static final String UPDATE="UPDATE ORDERS SET ID=:ID, CUST_ID=:CUST_ID, ORDER_DATE=:ORDER_DATE WHERE ID=:ID";
	private static final String DELETE="DELETE FROM ORDERS WHERE ID=:ID";
	private static final String ALL_ROWS="SELECT ID, CUST_ID, ORDER_DATE FROM ORDERS";

	@Override
	public List<Orders> list() {
		return  namedParameterJdbcTemplate.query(ALL_ROWS, new BeanPropertyRowMapper<Orders>(Orders.class));
	}

	@Override
	public int save(Orders orders) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("ID", orders.getId())
				.addValue("CUST_ID", orders.getCust_id())
				.addValue("ORDER_DATE", orders.getOrder_date());
		return namedParameterJdbcTemplate.update(SAVE, namedParameters);
	}

	@Override
	public Optional<Orders> get(int id) {
		Orders order=null;
		SqlParameterSource namedParameters = new MapSqlParameterSource("ID", id);
		try {
			order = namedParameterJdbcTemplate.queryForObject(GET, namedParameters, new BeanPropertyRowMapper<Orders>(Orders.class));
		} catch (DataAccessException e) {
			log.info("Order not found: "+id);
		}
		return Optional.ofNullable(order);
	}

	@Override
	public int update(Orders orders, int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("ID", orders.getId())
				.addValue("CUST_ID", orders.getCust_id())
				.addValue("ORDER_DATE", orders.getOrder_date());
		return namedParameterJdbcTemplate.update(UPDATE, namedParameters);
	}

	@Override
	public int delete(int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("ID", id);
		return namedParameterJdbcTemplate.update(DELETE, namedParameters);
	}

	
}
