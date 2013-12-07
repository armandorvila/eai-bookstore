package com.armandorv.miw.eai.bookstore.api.domain;

/**
 * Model a book of our book store.
 * 
 * @author armandorv
 * 
 */
public class Book {

	private Long id;

	private String name;

	private String description;

	private String isbn;
	
	private int stock;

	private boolean available;
	
	private double price;

	public Book() {
	}

	public Book(String name, String description, String isbn) {
		super();
		this.name = name;
		this.description = description;
		this.isbn = isbn;
	}
	
	public void incrementStock(int amount){
		stock += amount;
	}
	
	public void decrementStock(int amount){
		stock -= amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
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
		Book other = (Book) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", description="
				+ description + ", isbn=" + isbn + ", available=" + available
				+ ", price=" + price + "]";
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}