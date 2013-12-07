package com.armandorv.miw.eai.bookstore;

import org.mule.api.MuleException;
import org.mule.tck.junit4.FunctionalTestCase;

import com.armandorv.miw.eai.bookstore.api.domain.Book;
import com.armandorv.miw.eai.bookstore.api.domain.Customer;
import com.armandorv.miw.eai.bookstore.api.domain.Order;
import com.armandorv.miw.eai.bookstore.api.service.IBookService;
import com.armandorv.miw.eai.bookstore.api.service.ICustomerService;
import com.armandorv.miw.eai.bookstore.api.service.IShipmentService;

/**
 * Base class for all test cases of the application.
 * 
 * @author armandorv
 * 
 */
public abstract class AbstractFlowTest extends FunctionalTestCase {

	protected static final String VM_HTTP_INPUT_ENDPONT = "vm://httpInputEndpont";

	protected static final String SMTP_DISPATCH_NOTE_OUTBOUND_ENDPOINT = "vm://smtpDispatchNoteOutboundEndpont";
	protected static final String SMTP_EXPRESS_DISPATCH_NOTE_OUTBOUND_ENDPOINT = "vm://smtpExpressDispatchNoteOutboundEndpont";
	protected static final String SMTP_INVOICES_OUTBOUND_ENDPOINT = "vm://smtpInvoicesOutboundEndpont";
	protected static final String SMTP_NOTIFICATIONS_OUTBOUND_ENDPOINT = "vm://smtpNotificationsOutboundEndpont";

	protected static final String FILE_INPUT_ENDPOINT = "vm://fileInputEndpoint";
	protected static final String FILE_INVOICES_OUTBOUND_ENDPOINT = "vm://fileInvoicesOutboundEndpont";
	protected static final String FILE_DISPATCH_NOTE_OUTBOUND_ENDPOINT = "vm://fileDispatchNoteOutboundEndpont";
	protected static final String FILE_EXPRESS_DISPATCH_NOTE_OUTBOUND_ENDPOINT = "vm://fileExpressDispatchNoteOutboundEndpont";

	protected Customer armando;

	private Book springInAction;
	private Book muleInAction;
	private Book eai;

	protected ICustomerService customerService;
	protected IBookService bookService;
	protected IShipmentService shipmentService;

	@Override
	protected String getConfigResources() {
		return "bookstore-mule-config-test.xml";
	}

	public void setUp() throws MuleException {
		customerService = muleContext.getRegistry().lookupObject(
				ICustomerService.class);
		bookService = muleContext.getRegistry().lookupObject(
				IBookService.class);

		armando = customerService.findCusomter("62789475D");

		springInAction = bookService.findBook("1");
		muleInAction = bookService.findBook("2");
		eai = bookService.findBook("3");
	}

	protected Order springInAction(int amount) {
		Order order = new Order(springInAction, amount);
		order.setCustomer(armando);
		return order;
	}

	protected Order muleInAction(int amount) {
		Order order = new Order(muleInAction, amount);
		order.setCustomer(armando);
		return order;
	}

	protected Order eai(int amount) {
		Order order = new Order(eai, amount);
		order.setCustomer(armando);
		return order;
	}
}
