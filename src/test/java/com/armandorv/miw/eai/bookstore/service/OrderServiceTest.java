package com.armandorv.miw.eai.bookstore.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.miw.eai.bookstore.AbstractServiceTest;
import com.armandorv.miw.eai.bookstore.api.domain.Order;
import com.armandorv.miw.eai.bookstore.api.service.IBookService;
import com.armandorv.miw.eai.bookstore.api.service.ICustomerService;
import com.armandorv.miw.eai.bookstore.api.service.IOrderService;

public class OrderServiceTest extends AbstractServiceTest {

	@Autowired
	private IOrderService orderService;

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private IBookService bookService;

	@Test
	public void testSaveOrder() {
		Order order = new Order(bookService.findBook("1"), 2);
		order.setCustomer(customerService.findCusomter(ARMANDO_NIF));

		order.setDate(new Date());

		orderService.saveOrder(order);
	}

}
