package com.armandorv.miw.eai.bookstore.impl.transformer;

import java.util.HashSet;
import java.util.Set;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import com.armandorv.miw.eai.bookstore.api.domain.Order;

public class FileToOrdersTransofmer extends AbstractTransformer {

	@Override
	protected Object doTransform(Object src, String enc)
			throws TransformerException {
		Set<Order> orders = new HashSet<>();
		
		return orders;
	}

}
