package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

public class Orders implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7251049066816066098L;
	private long id;
	private long cust_id;
	private Date order_date;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCust_id() {
		return cust_id;
	}
	public void setCust_id(long cust_id) {
		this.cust_id = cust_id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	@Override
	public String toString() {
		return "Orders [id=" + id + ", cust_id=" + cust_id + ", order_date=" + order_date + "]";
	}

}
