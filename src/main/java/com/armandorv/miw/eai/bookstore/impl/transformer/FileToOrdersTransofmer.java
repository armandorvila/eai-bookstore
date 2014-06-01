package com.armandorv.miw.eai.bookstore.impl.transformer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.mule.api.transformer.TransformerException;

import com.armandorv.miw.eai.bookstore.api.domain.Customer;
import com.armandorv.miw.eai.bookstore.api.domain.Order;

/**
 * This transformer receives an String with the file content and return a set of
 * Orders to be processed.
 * 
 * @author armandorv
 * 
 */
public class FileToOrdersTransofmer extends BookstoreAbstactTransformer {

	private static final Logger logger = Logger
			.getLogger(FileToOrdersTransofmer.class);

	@Override
	protected Object doTransform(Object src, String enc)
			throws TransformerException {

		logger.info("Start parsing orders file.");

		String[] lines = ((String) src).split("\n");
		Customer customer = saveOrUpdateCustomer(parseCustomer(lines[1]
				.split(";")));

		logger.info("Read " + lines.length + " for customer "
				+ customer.getNif());

		Set<Order> orders = new HashSet<>();

		for (int i = 3; i < lines.length; i++) {
			Order order = parseOrder(lines[i].split(";"));
			if (order.getBook() != null) {
				order.setCustomer(customer);
				order.setDate(new Date());
				super.saveOrder(order);
				orders.add(order);
			} else {
				logger.warn("A non existent book was received. ISBN :  "
						+ lines[i].split(";")[0]);
			}
		}
		return orders;
	}

	private Order parseOrder(String[] orderLine) {
		return new Order(super.findBook(orderLine[0]),
				Integer.parseInt(orderLine[1]));
	}

	private Customer parseCustomer(String[] customerLine) {
		Customer customer = new Customer(customerLine[1], customerLine[0],
				customerLine[2]);
		customer.setMail(customerLine[3]);
		customer.setLoan("S".equalsIgnoreCase(customerLine[4]));
		return customer;
	}

}