package com.armandorv.miw.eai.bookstore.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.miw.eai.bookstore.AbstractServiceTest;
import com.armandorv.miw.eai.bookstore.api.domain.Book;
import com.armandorv.miw.eai.bookstore.api.domain.BookWaitingList;
import com.armandorv.miw.eai.bookstore.api.domain.Customer;
import com.armandorv.miw.eai.bookstore.api.service.IBookService;
import com.armandorv.miw.eai.bookstore.api.service.ICustomerService;

public class BookServiceTest extends AbstractServiceTest {

	@Autowired
	private IBookService bookService;
	
	@Autowired
	private ICustomerService customerService;

	@Test
	public void testIsAvailable() {
		Assert.assertNotNull(bookService);

		Assert.assertTrue(bookService.isAvailable(new Book(null, null, "3")));
		Assert.assertTrue(bookService.isAvailable(new Book(null, null, "3"), 9));
		Assert.assertFalse(bookService.isAvailable(new Book(null, null, "3"),
				90));
	}

	@Test
	public void testFindBook() {

		Book book = bookService.findBook("1");
		Assert.assertNotNull(book);

		Assert.assertEquals("1", book.getIsbn());
	}

	@Test
	public void updateBook() {

		Book book = bookService.findBook("1");
		int stock = book.getStock();

		book.setStock(stock + 10);
		bookService.updateBook(book);

		book = bookService.findBook("1");
		Assert.assertEquals(stock + 10, book.getStock());
	}

	@Test
	public void testFindBookWaitingList() {
		BookWaitingList list = bookService.findWaitingList(new Book(
				"not used in sql", "not used in sql", "1"));
		Assert.assertNotNull(list);
		Assert.assertNotNull(list.getBook());
		Assert.assertNotNull(list.getCusomters());
		
		Assert.assertFalse(list.getCusomters().isEmpty());
	}

	@Test
	public void addToWaitingList() {
		BookWaitingList list = bookService.findWaitingList(new Book(
				"not used in sql", "not used in sql", "1"));
		Assert.assertFalse(list.getCusomters().isEmpty());
		
		int customersBefore = list.getCusomters().size();
		
		Customer customer = customerService.findCusomter("65459475D");
		
		bookService.addToWaitingList(list.getBook(), customer);
		
		list = bookService.findWaitingList(list.getBook());
		
		Assert.assertEquals(customersBefore + 1, list.getCusomters().size());
	}

}
