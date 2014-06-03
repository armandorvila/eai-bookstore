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
import com.armandorv.miw.eai.bookstore.api.domain.Notification;
import com.armandorv.miw.eai.bookstore.api.domain.Order;

public class NonStockOrdersTest extends AbstractFlowTest {

	protected MuleClient client;

	private Set<Order> orders = new HashSet<Order>();

	@Before
	public void setUp() throws MuleException {
		super.setUp();
		client = new MuleClient(muleContext);
		orders.add(super.muleInAction(1000));
	}

	/**
	 * This test send an order which is not in stock (see in memory script.)
	 * 
	 * <ul>
	 * <li>Check stock for each order (Only one and there is not in stock.)</li>
	 * <li>Send denied notification.</li>
	 * </ul>
	 * 
	 * As result of this test the final end-point must has no order. HOwever the
	 * notifications endpoint must has one notification.
	 * 
	 */
	@Test
	public void testFlowWithoutStock() throws MuleException {
		client.send(VM_HTTP_INPUT_ENDPONT, orders, null);

		assertEmptyOutboundEndpoints();

		MuleMessage notificationMessage = client.request(
				SMTP_NOTIFICATIONS_OUTBOUND_ENDPOINT, 5000L);
		
		Assert.assertNotNull(notificationMessage);

		Notification notification = notificationMessage
				.getPayload(Notification.class);

		Assert.assertNotNull(notification);
		Assert.assertEquals(armando, notification.getCustomer());
	}

	/**
	 * Assert that some end points which must be empty after the execution are
	 * empty as expected.
	 */
	private void assertEmptyOutboundEndpoints() throws MuleException {
		Assert.assertNull(client
				.request(SMTP_INVOICES_OUTBOUND_ENDPOINT, 1000L));

		Assert.assertNull(client.request(SMTP_DISPATCH_NOTE_OUTBOUND_ENDPOINT,
				1000L));

		Assert.assertNull(client.request(
				SMTP_EXPRESS_DISPATCH_NOTE_OUTBOUND_ENDPOINT, 1000L));
	}

}