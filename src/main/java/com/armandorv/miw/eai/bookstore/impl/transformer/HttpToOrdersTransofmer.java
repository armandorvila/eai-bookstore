package com.armandorv.miw.eai.bookstore.impl.transformer;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.types.DataTypeFactory;

import com.armandorv.miw.eai.bookstore.api.domain.Customer;
import com.armandorv.miw.eai.bookstore.api.domain.Order;

public class HttpToOrdersTransofmer extends BookstoreAbstactTransformer {

	public HttpToOrdersTransofmer() {
		super.registerSourceType(DataTypeFactory.create(Map.class));
		super.setReturnDataType(DataTypeFactory.create(Set.class));
	}

	@Override
	protected Object doTransform(Object src, String enc)
			throws TransformerException {

		@SuppressWarnings("unchecked")
		Map<String, String> httpParams = (Map<String, String>) src;

		Customer customer = saveOrUpdateCustomer(parseCustomer(httpParams));

		Set<Order> orders = new HashSet<>();
		Order order = parseOrder(httpParams);
		if (order.getBook() != null) {
			order.setCustomer(customer);
			order.setDate(new Date());

			super.saveOrder(order);

			orders.add(order);
		} else {
			logger.warn("A non existent book was received. ISBN :  "
					+ httpParams.get("book"));
		}
		return orders;
	}

	private Order parseOrder(Map<String, String> httpParams) {
		return new Order(super.findBook(httpParams.get("book")),
				Integer.parseInt(httpParams.get("amount")));
	}

	private Customer parseCustomer(Map<String, String> httpParams) {
		Customer customer = new Customer();
		customer.setName(httpParams.get("name"));
		customer.setNif(httpParams.get("nif"));
		customer.setAddress(httpParams.get("address"));
		customer.setMail(httpParams.get("mail"));
		customer.setLoan(httpParams.get("loan") != null);
		return customer;
	}

}