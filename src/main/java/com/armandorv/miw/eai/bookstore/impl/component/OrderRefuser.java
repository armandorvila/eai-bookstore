package com.armandorv.miw.eai.bookstore.impl.component;

import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.miw.eai.bookstore.api.domain.BookWaitingList;
import com.armandorv.miw.eai.bookstore.api.domain.Notification;
import com.armandorv.miw.eai.bookstore.api.domain.Order;
import com.armandorv.miw.eai.bookstore.api.service.IBookService;

/**
 * Mule component intended to include a Customer in the waiting list of a Book
 * and produces a notification for him.
 * 
 * @author armandorv
 * 
 */
public class OrderRefuser {

	private static final String SUBJECT = "Order refused";
	@Autowired
	private IBookService bookService;

	public Notification deniedOrder(Order order) {
		includeInWaitingList(order);
		return notification(order);
	}

	private Notification notification(Order order) {
		Notification notification = new Notification();

		notification.setCustomer(order.getCustomer());
		notification.setSubject(SUBJECT);
		notification.setMessage("The order for the book " + order.getBook().getName()
				+ " has not been delivered cause such book is not in stock.");
		return notification;
	}

	private void includeInWaitingList(Order order) {
		BookWaitingList list = bookService.findWaitingList(order.getBook());
		if (!list.getCusomters().contains(order.getCustomer())) {
			bookService.addToWaitingList(order.getBook(), order.getCustomer());
		}
	}
}
