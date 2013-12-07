package com.armandorv.miw.eai.bookstore.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.miw.eai.bookstore.AbstractServiceTest;
import com.armandorv.miw.eai.bookstore.api.domain.Invoice;
import com.armandorv.miw.eai.bookstore.api.service.IInvoiceService;

public class InvoiceServiceTest extends AbstractServiceTest {

	@Autowired
	private IInvoiceService invoiceService;

	@Test
	public void testSaveInvoice() {
		Assert.assertNotNull(invoiceService);

		Invoice invoice = new Invoice();
		invoice.setDate(new Date());
		invoice.setImporte(2000.0);

		invoiceService.saveInvoice(invoice);
	}
}
