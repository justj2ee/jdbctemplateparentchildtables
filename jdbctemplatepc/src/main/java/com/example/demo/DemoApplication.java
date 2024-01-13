package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.model.Customer;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	
	
	@Autowired  private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		CustomerDAO customerDAO=(CustomerDAO) applicationContext.getBean("customerDAO");
		
		List<Customer> customerList = customerDAO.list();
		customerList.forEach(System.out::println);
		
		Customer customer = new Customer();
		customer.setfName("Darlene");
		customer.setlName("Jones");
		customer.setId(3);
		customer.setAddress("405 Marberry Road");
		customer.setCity("OceanCity");
		customer.setState("MD");
		customer.setZip_cd("089011");
		//customerDAO.save(customer);
		
		customerList = customerDAO.list();
		customerList.forEach(System.out::println);
		customer.setlName("Obama");
		customerDAO.update(customer, 2);
		customerList = customerDAO.list();
		customerList.forEach(System.out::println);
		
		customerDAO.delete(2);
		customerList = customerDAO.list();
		customerList.forEach(System.out::println);
		
		Customer cust = new Customer();
		cust.setfName("Darlene");
		cust.setlName("Jones");
		cust.setId(2);
		cust.setAddress("405 Marberry Road");
		cust.setCity("OceanCity");
		cust.setState("MD");
		cust.setZip_cd("089011");
		customerDAO.save(cust);
		
		customerList = customerDAO.list();
		customerList.forEach(System.out::println);
		
	}
	
	

}
