package com.armandorv.miw.eai.bookstore.impl.component;

import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.miw.eai.bookstore.api.domain.Invoice;
import com.armandorv.miw.eai.bookstore.api.domain.Order;
import com.armandorv.miw.eai.bookstore.api.domain.Shipment;
import com.armandorv.miw.eai.bookstore.api.service.IInvoiceService;

public class InvoiceGenerator {
	
	@Autowired
	private IInvoiceService invoiceService;

	public Shipment generateInvoice(Shipment shipment) {
		Invoice invoice = new Invoice();

		for (Order order : shipment.getOrders()) {
			for (int i = 0; i < order.getAmount(); i++) {
				invoice.addBook(order.getBook());
			}
		}

		invoice.calcularImporte();
		try {
		invoiceService.saveInvoice(invoice);
		}
		catch(Throwable t){
			t.printStackTrace();
			throw t;
		}

		shipment.setInvoice(invoice);
		return shipment;
	}
}
