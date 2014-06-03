package com.armandorv.miw.eai.bookstore.flows;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;

import com.armandorv.miw.eai.bookstore.AbstractFlowTest;
import com.armandorv.miw.eai.bookstore.api.domain.Invoice;
import com.armandorv.miw.eai.bookstore.api.domain.Order;
import com.armandorv.miw.eai.bookstore.api.domain.Shipment;

/**
 * This class tests the application flows using vm enpoints instead of real
 * HTTP, FILE or SMPT end points.
 * 
 * This test process two Orders in stock and without Loan.
 * 
 * @author armandorv
 * 
 */
public class InStockOrdersWithoutLoanTest extends AbstractFlowTest {

	private MuleClient client;

	private Set<Order> orders = new HashSet<>();

	@Before
	public void setUp() throws MuleException {
		super.setUp();

		client = new MuleClient(muleContext);

		orders.add(springInAction(2));
		orders.add(muleInAction(2));

		armando.setLoan(false);
	}

	/**
	 * This method send a message to a fake VM inboud endpoint. This has the
	 * same effect that if we start after our HttpTpOrdersTransformer.
	 * 
	 * In this case we are going to send two orders with Armando as customer.
	 * This orders are in stock and have no loan requested.
	 * 
	 * So the path of this test is :
	 * 
	 * <ul>
	 * <li>Check stock for each order, true for both.</li>
	 * <li>Split the collection</li>
	 * <li>Generate invoice (We test the generated invoice at
	 * VM_FILE_OUTPUT_ENDPONT).</li>
	 * <li>Carry out the shipment: Armando is not vio so the shipment is
	 * ordinary.</li>
	 * </ul>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFlowInStockWithNoLoan() throws Exception {
		client.send(VM_HTTP_INPUT_ENDPONT, orders, null);

		// Assert invoice is in SMTP and file outs
		MuleMessage invoice = client.request(FILE_INVOICES_OUTBOUND_ENDPOINT,
				5000L);
		assertInvoice(invoice.getPayload(Invoice.class));
		
		invoice = client.request(SMTP_INVOICES_OUTBOUND_ENDPOINT,
				5000L);
		assertInvoice(invoice.getPayload(Invoice.class));
		
		
		// Assert dispatch note is in SMTP and file outs
		MuleMessage note = client.request(FILE_DISPATCH_NOTE_OUTBOUND_ENDPOINT,
				5000L);
		assertDispatchNote(note.getPayload(Shipment.class));
		
		note = client.request(SMTP_DISPATCH_NOTE_OUTBOUND_ENDPOINT,
				5000L);
		assertDispatchNote(note.getPayload(Shipment.class));

	}

	private void assertInvoice(Invoice invoice) {
		Assert.assertNotNull(invoice);
		Assert.assertEquals(4, invoice.getBooks().size());
		Assert.assertEquals(new Double(290.4), invoice.getImporte());
	}

	private void assertDispatchNote(Shipment note) {
		Assert.assertNotNull(note);
		Assert.assertNotNull(note.getCustomer());
		Assert.assertEquals(armando, note.getCustomer());
		Assert.assertFalse(note.isExpress());
	}
}