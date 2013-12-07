package com.armandorv.miw.eai.bookstore.impl.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.miw.eai.bookstore.api.domain.Book;
import com.armandorv.miw.eai.bookstore.api.service.IBookService;

public class BooksCatalogProvider {

	@Autowired
	private IBookService bookService;


	public List<Book> provide(String payload) {
		return bookService.findAll();
	}
}
