package com.armandorv.miw.eai.bookstore.impl.component;

import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.miw.eai.bookstore.api.domain.Book;
import com.armandorv.miw.eai.bookstore.api.domain.Order;
import com.armandorv.miw.eai.bookstore.api.service.IBookService;

/**
 * Mule component used in the process order in stock flow.
 * 
 * @author armandorv
 * 
 */
public class OrderProcessor{

	@Autowired
	private IBookService bookService;

	/**
	 * Decrement the stock of the book.
	 */
	public Order processOrder(Order order) {
		
		
		Book book = bookService.findBook(order.getBook().getIsbn());
		book.decrementStock(order.getAmount());
		bookService.updateBook(book);
		
		return order;
	}

}
