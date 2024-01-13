package com.example.demo.model;

import java.io.Serializable;

public class OrderItems implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4975347977874966586L;
	private long id;
	private long order_id;
	private long prod_id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public long getProd_id() {
		return prod_id;
	}
	public void setProd_id(long prod_id) {
		this.prod_id = prod_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "OrderItems [id=" + id + ", order_id=" + order_id + ", prod_id=" + prod_id + "]";
	}

}
