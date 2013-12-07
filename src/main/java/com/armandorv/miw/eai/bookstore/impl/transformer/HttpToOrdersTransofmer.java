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

		Customer customer = customer(httpParams);
		customer = saveOrUpdateCustomer(customer);
		customer.setLoan(httpParams.get("loan") != null);

		Order order = new Order();

		order.setCustomer(customer);
		order.setBook(book(httpParams.get("book")));
		order.setAmount(Integer.parseInt(httpParams.get("amount")));
		order.setDate(new Date());

		saveOrder(order);

		Set<Order> orders = new HashSet<>();
		orders.add(order);
		return orders;
	}

}