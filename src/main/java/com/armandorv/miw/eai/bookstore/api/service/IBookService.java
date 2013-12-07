package com.armandorv.miw.eai.bookstore.api.service;

import java.util.List;

import com.armandorv.miw.eai.bookstore.api.domain.Book;
import com.armandorv.miw.eai.bookstore.api.domain.BookWaitingList;
import com.armandorv.miw.eai.bookstore.api.domain.Customer;

/**
 * Books service intended to contain business logic related with books.
 * 
 * @author armandorv
 * 
 */
public interface IBookService {

	/**
	 * Check if there is stock for the current book.
	 * 
	 * @param book
	 *            book the book.
	 * @return true if the book has stock yet.
	 */
	boolean isAvailable(Book book);

	/**
	 * Check if there is the desired number of books in stock.
	 * 
	 * @param book
	 * @param number
	 * @return
	 */
	boolean isAvailable(Book book, int number);

	Book findBook(String isbn);

	List<Book> findAll();

	void updateBook(Book book);

	/**
	 * It finds the waiting list of a book.
	 * 
	 * @param book
	 * @return
	 */
	BookWaitingList findWaitingList(Book book);

	/**
	 * It updates the waiting list for a book.
	 * 
	 * @param book
	 */
	void addToWaitingList(Book book, Customer customer);
}
