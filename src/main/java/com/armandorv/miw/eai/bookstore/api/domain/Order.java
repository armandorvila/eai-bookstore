package com.armandorv.miw.eai.bookstore.api.domain;

import java.util.Date;

/**
 * Model an order of a batch. An order is only for book, in any amount.
 * 
 * @author armandorv
 * 
 */
public class Order {

	private Long id;

	private Book book;

	private Integer amount;

	private Date date;

	private boolean inStock;

	private Customer customer;

	public Order() {
	}

	public Order(Book book, Integer number) {
		super();
		this.book = book;
		this.amount = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public boolean isInStock() {
		return inStock;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
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
		Order other = (Order) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", book=" + book + ", ammount=" + amount
				+ ", inStock=" + inStock + ", customer=" + customer + "]";
	}

	public Date getDate() {
		if (date != null) {
			return (Date) date.clone();
		} else {
			return date;
		}
	}

	public void setDate(Date date) {
		if (date != null) {
			this.date = (Date) date.clone();
		} else {
			this.date = date;
		}
	}

}