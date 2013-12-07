package com.armandorv.miw.eai.bookstore.transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.Transformer;
import org.mule.api.transformer.TransformerException;
import org.mule.tck.junit4.FunctionalTestCase;

import com.armandorv.miw.eai.bookstore.api.domain.Book;
import com.armandorv.miw.eai.bookstore.api.domain.Customer;
import com.armandorv.miw.eai.bookstore.api.domain.Invoice;
import com.armandorv.miw.eai.bookstore.api.domain.Order;
import com.armandorv.miw.eai.bookstore.api.domain.Shipment;


public class ShipmentXslTransformerTest extends FunctionalTestCase {

	private static Logger logger = Logger
			.getLogger(ShipmentXslTransformerTest.class);

	private Transformer xslTransformer;

	private Transformer xmlTransformer;

	private MuleMessage source;

	@Before
	public void setup() {
		xmlTransformer = muleContext.getRegistry().lookupTransformer(
				"shipment2XmlTransformer");
		xslTransformer = muleContext.getRegistry().lookupTransformer(
				"shipmentXsltTransformer");

		Assert.assertNotNull(xmlTransformer);
		Assert.assertNotNull(xslTransformer);

		List<Book> books = new ArrayList<>();
		books.add(new Book("Jenkins definitive guide",
				"A guide to implement CI and CD", "9089898"));
		books.add(new Book("Jenkins definitive guide",
				"A guide to implement CI and CD", "9089898"));

		Invoice invoice = new Invoice(books);

		invoice.setNumber(UUID.randomUUID().toString());
		invoice.setDate(new Date());
		invoice.setImporte(40D);
		
		Customer customer = new Customer("Armando", "343434", "sdsdsds");
		customer.setMail("hi@gmail.com");
		
		Shipment shipment = new Shipment();
		shipment.setCustomer(customer);
		shipment.addOrder(new Order(books.get(0), 2));
		shipment.addOrder(new Order(books.get(1), 2));
		
		shipment.setDate(new Date());
		shipment.setExpress(false);
		shipment.setShipmentNumber(UUID.randomUUID().toString());
		shipment.setInvoice(invoice);

		source = new DefaultMuleMessage(shipment, muleContext);
		Assert.assertNotNull(source.getPayload());
	}

	@Override
	protected String getConfigResources() {
		return "bookstore-mule-config-test.xml";
	}

	@Test
	public void testTransformInvoice() throws TransformerException, IOException {

		String xmlShipment= (String) xmlTransformer.transform(source);
		
		Assert.assertNotNull(xmlShipment);
		Assert.assertTrue(xmlShipment.contains("shipment"));
		Assert.assertTrue(xmlShipment.contains("customer"));
		Assert.assertTrue(xmlShipment.contains("invoice"));
		Assert.assertTrue(xmlShipment.contains("book"));
		
		logger.info(xmlShipment);

		byte[] htmlShipment = (byte[]) xslTransformer.transform(xmlShipment);
		Assert.assertNotNull(htmlShipment);

		IOUtils.write(htmlShipment, System.out);
	}

}
