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

import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Product;
@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {
	@Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static final Logger log = LoggerFactory.getLogger(CustomerDAOImpl.class);
	private static final String ALL_ROWS="SELECT ID, DESCRIPTION, PRICE FROM PRODUCT";
	private static final String SAVE="INSERT INTO PRODUCT(ID, DESCRIPTION, PRICE) VALUES (:ID, :DESCRIPTION, :PRICE)";
	private static final String UPDATE="UPDATE PRODUCT SET ID=:ID, DESCRIPTION=:DESCRIPTION, PRICE=:PRICE WHERE ID=:ID";
	private static final String GET="SELECT ID, DESCRIPTION, PRICE FROM PRODUCT WHERE ID=:ID";
	private static final String DELETE="DELETE FROM PRODUCT WHERE ID=:ID";

	@Override
	public List<Product> list() {
		return  namedParameterJdbcTemplate.query(ALL_ROWS, new BeanPropertyRowMapper<Product>(Product.class));
	}

	@Override
	public int save(Product product) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("ID", product.getId())
				.addValue("DESCRIPTION", product.getDescription())
				.addValue("PRICE", product.getPrice());
		return namedParameterJdbcTemplate.update(SAVE, namedParameters);
	}

	@Override
	public Optional<Product> get(int id) {
		Product product=null;
		SqlParameterSource namedParameters = new MapSqlParameterSource("ID", id);
		try {
			product = namedParameterJdbcTemplate.queryForObject(GET, namedParameters, new BeanPropertyRowMapper<Product>(Product.class));
		} catch (DataAccessException e) {
			log.info("Product not found: "+id);
		}
		return Optional.ofNullable(product);
	}

	@Override
	public int update(Product product, int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("ID", product.getId())
				.addValue("DESCRIPTION", product.getDescription())
				.addValue("PRICE", product.getPrice());
		return namedParameterJdbcTemplate.update(UPDATE, namedParameters);
	}

	@Override
	public int delete(int id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("ID", id);
		return namedParameterJdbcTemplate.update(DELETE, namedParameters);
	}

}
