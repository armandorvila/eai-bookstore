package com.armandorv.miw.eai.bookstore.impl.transformer;

import java.util.Map;

import org.mule.transformer.AbstractTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.miw.eai.bookstore.api.domain.Book;
import com.armandorv.miw.eai.bookstore.api.domain.Customer;
import com.armandorv.miw.eai.bookstore.api.domain.Order;
import com.armandorv.miw.eai.bookstore.api.service.IBookService;
import com.armandorv.miw.eai.bookstore.api.service.ICustomerService;
import com.armandorv.miw.eai.bookstore.api.service.IOrderService;

public abstract class BookstoreAbstactTransformer extends AbstractTransformer {

	@Autowired
	private IBookService bookService;

	@Autowired
	private IOrderService orderService;

	@Autowired
	private ICustomerService customerService;

	protected Book book(String isbn) {
		return bookService.findBook(isbn);
	}

	protected void saveOrder(Order order) {
		orderService.saveOrder(order);
	}

	//TODO users must be authenticated
	protected Customer saveOrUpdateCustomer(Customer customer) {
		Customer current = customerService.findCusomter(customer.getNif());

		if (current == null) {
			customerService.saveCustomer(customer);
		} else {
			current.setName(customer.getName());
			current.setAddress(customer.getAddress());
			current.setMail(customer.getMail());
			customerService.updateCustomer(current);
		}
		return customerService.findCusomter(customer.getNif());
	}

	protected Customer customer(Map<String, String> httpParams) {
		Customer customer = new Customer();
		customer.setName(httpParams.get("name"));
		customer.setNif(httpParams.get("nif"));
		customer.setAddress(httpParams.get("address"));
		customer.setMail(httpParams.get("mail"));
		return customer;
	}
}
