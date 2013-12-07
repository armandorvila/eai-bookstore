package com.armandorv.miw.eai.bookstore.api.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This entity model the waiting list for a book which is not in stock.
 * 
 * @author armandorv
 * 
 */
public class BookWaitingList {

	private Book book;

	private List<Customer> cusomters = new ArrayList<>();
	
	public BookWaitingList(){
	}

	public BookWaitingList(Book book) {
		super();
		this.book = book;
	}

	public BookWaitingList(Book book, List<Customer> cusomters) {
		super();
		this.book = book;
		this.cusomters = cusomters;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void addCustomer(Customer customer){
		cusomters.add(customer);
	}
	
	public List<Customer> getCusomters() {
		return  Collections.unmodifiableList(cusomters);
	}

	public void setCusomters(List<Customer> cusomters) {
		this.cusomters = cusomters;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
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
		BookWaitingList other = (BookWaitingList) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WaitingList [book=" + book + ", cusomters=" + cusomters + "]";
	}
	
}