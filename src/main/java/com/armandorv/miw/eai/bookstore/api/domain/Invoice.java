package com.armandorv.miw.eai.bookstore.api.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Invoice {

	private static final Double iva = 0.21;

	private String number = UUID.randomUUID().toString(); 

	private Long id;

	private Date date = new Date();

	private Double importe;

	protected List<Book> books = new ArrayList<>();

	public Invoice(){}
	
	public Invoice(List<Book> books) {
		this.books = books;
		this.calcularImporte();
	}
	
	public void addBook(Book book){
		books.add(book);
	}

	public Long getId() {
		return id;
	}

	public Double calcularImporte() {
		importe = 0.0;
		for (Book book : books) {
			importe += book.getPrice();
		}
		importe = importe + importe * iva;

		return importe;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public List<Book> getBooks() {
		return Collections.unmodifiableList(books);
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Invoice [number=" + number + ", id=" + id + ", date=" + date
				+ ", importe=" + importe + ", books=" + books + "]";
	}

}