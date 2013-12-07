package com.armandorv.miw.eai.bookstore.api.domain;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Shipment {

	private Long id;

	private Date date;

	private String shipmentNumber;

	private boolean express;

	private Customer customer;

	private Invoice invoice;

	private Set<Order> orders = new HashSet<>();

	public double shipmentPrice() {
		double spentAmount = 0D;
		for (Order order : orders) {
			spentAmount = order.getBook().getPrice() * order.getAmount();
		}
		return spentAmount;
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	public void removeOrder(Order order) {
		orders.remove(order);
	}

	public Set<Order> getOrders() {
		return Collections.unmodifiableSet(orders);
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isExpress() {
		return express;
	}

	public void setExpress(boolean express) {
		this.express = express;
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((shipmentNumber == null) ? 0 : shipmentNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shipment other = (Shipment) obj;
		if (shipmentNumber == null) {
			if (other.shipmentNumber != null)
				return false;
		} else if (!shipmentNumber.equals(other.shipmentNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Shipment [id=" + id + ", date=" + date + ", shipmentNumber="
				+ shipmentNumber + ", express=" + express + ", customer="
				+ customer + ", invoice=" + invoice + "]";
	}

}