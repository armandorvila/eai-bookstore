package com.armandorv.miw.eai.bookstore.transformer;

import java.io.IOException;

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

import com.armandorv.miw.eai.bookstore.api.domain.Customer;
import com.armandorv.miw.eai.bookstore.api.domain.Notification;


public class NotificationXslTransformerTest extends FunctionalTestCase {

	private static Logger logger = Logger
			.getLogger(NotificationXslTransformerTest.class);

	private Transformer xslTransformer;

	private Transformer xmlTransformer;

	private MuleMessage source;

	@Before
	public void setup() {
		xmlTransformer = muleContext.getRegistry().lookupTransformer(
				"notification2XmlTransformer");
		xslTransformer = muleContext.getRegistry().lookupTransformer(
				"notificationXsltTransformer");

		Assert.assertNotNull(xmlTransformer);
		Assert.assertNotNull(xslTransformer);

		Customer customer = new Customer("Armando", "343434", "sdsdsds");
		customer.setMail("hi@gmail.com");
		
		Notification notification = new Notification();
		notification.setCustomer(customer);
		notification.setSubject("Order refused.");
		notification.setMessage("Your order has been refused ...");
		
		source = new DefaultMuleMessage(notification, muleContext);
		Assert.assertNotNull(source.getPayload());
	}

	@Override
	protected String getConfigResources() {
		return "bookstore-mule-config-test.xml";
	}

	@Test
	public void testTransformNotification() throws TransformerException, IOException {

		String xmlNotification= (String) xmlTransformer.transform(source);
		
		Assert.assertNotNull(xmlNotification);
		Assert.assertTrue(xmlNotification.contains("notification"));
		Assert.assertTrue(xmlNotification.contains("customer"));
		
		logger.info(xmlNotification);

		byte[] htmlNotification = (byte[]) xslTransformer.transform(xmlNotification);
		Assert.assertNotNull(htmlNotification);

		IOUtils.write(htmlNotification, System.out);
	}

}
