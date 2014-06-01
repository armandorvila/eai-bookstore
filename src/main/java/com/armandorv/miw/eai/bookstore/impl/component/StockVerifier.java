package com.armandorv.miw.eai.bookstore.impl.component;

import org.mule.api.transformer.TransformerException;
import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.miw.eai.bookstore.api.domain.Order;
import com.armandorv.miw.eai.bookstore.api.service.IBookService;

public class StockVerifier {

	@Autowired
	private IBookService bookService;

	public Order checkStock(Order order) throws TransformerException {

		boolean inStock = bookService.isAvailable(order.getBook(),
				order.getAmount());
		
		if(inStock){
			CorrlationGroupSize.getINSTANCE().increment();
		}
		
		order.setInStock(inStock);
		return order;
	}
}
