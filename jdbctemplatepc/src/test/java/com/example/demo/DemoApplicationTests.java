package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired CustomerDAO customerDAO;
	@Autowired ProductDAO productDAO;
	
	@Test
	@Order(1)
	void getCustomerTest() {
		Optional<Customer> customer = customerDAO.get(1);
		if (customer.isPresent())
			assertEquals("Thomas",customer.get().getfName());
	}
	
	@Test
	@Order(2)
	void saveCustomerTest() {
		Customer customer = new Customer();
		customer.setId(5);
		customer.setfName("Woody");
		customer.setlName("Harrelson");
		customer.setAddress("100 Rodeo Drive");
		customer.setCity("Hollywood");
		customer.setState("CA");
		customer.setZip_cd("01010");
		int rowsSaved=customerDAO.save(customer);
		assertEquals(1,rowsSaved);
	}
	
	@Test
	@Order(3)
	void deleteCustomerTest() {
		int rowsDeleted=customerDAO.delete(5);
		assertEquals(1,rowsDeleted);
	}
	
	@Test
	@Order(4)
	void getProductTest() {
		Optional<Product> product = productDAO.get(0);
		if (product.isPresent()) 
			assertEquals("Red Snapper", product.get().getDescription());
	}
	
	@Test
	@Order(5)
	void saveProductTest() {
		Product product = new Product();
		product.setId(7);
		product.setDescription("Burt Bees Lip Balm");
		product.setPrice(new BigDecimal("2.75"));
		int rowsSaved=productDAO.save(product);
		assertEquals(1,rowsSaved);
	}
	
	@Test
	@Order(6)
	void deleteProductTest() {
		int rowsDeleted=productDAO.delete(7);
		assertEquals(1,rowsDeleted);
	}

}
