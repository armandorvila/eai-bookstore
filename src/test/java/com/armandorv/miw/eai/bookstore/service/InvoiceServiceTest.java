package com.armandorv.miw.eai.bookstore.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import com.armandorv.miw.eai.bookstore.AbstractServiceTest;
import com.armandorv.miw.eai.bookstore.api.domain.Invoice;
import com.armandorv.miw.eai.bookstore.api.service.IInvoiceService;

@DirtiesContext
public class InvoiceServiceTest extends AbstractServiceTest {

	@Autowired
	private IInvoiceService invoiceService;

	@Test
	public void testFind(){
		Invoice invoice = invoiceService.find(1000L);
		Assert.assertNotNull(invoice);
	}
	
	@Test
	public void testFindByNumber(){
		Invoice invoice = invoiceService.findByNumber(invoiceService.find(1000L).getNumber());
		Assert.assertNotNull(invoice);
	}
	
	@Test
	public void testSaveInvoice() {
		Assert.assertNotNull(invoiceService);

		Invoice invoice = new Invoice();
		invoice.setDate(new Date());
		invoice.setImporte(2000.0);

		invoiceService.saveInvoice(invoice);
	}
}
