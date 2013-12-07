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
import com.armandorv.miw.eai.bookstore.api.domain.Invoice;


public class InvoiceXslTransformerTest extends FunctionalTestCase {

	private static Logger logger = Logger
			.getLogger(InvoiceXslTransformerTest.class);

	private Transformer xslTransformer;

	private Transformer xmlTransformer;

	private MuleMessage source;

	@Before
	public void setup() {
		xmlTransformer = muleContext.getRegistry().lookupTransformer(
				"invoice2XmlTransformer");
		xslTransformer = muleContext.getRegistry().lookupTransformer(
				"invoiceXsltTransformer");

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

		source = new DefaultMuleMessage(invoice, muleContext);
		Assert.assertNotNull(source.getPayload());
	}

	@Override
	protected String getConfigResources() {
		return "bookstore-mule-config-test.xml";
	}

	@Test
	public void testTransformInvoice() throws TransformerException, IOException {

		String xmlInvoice = (String) xmlTransformer.transform(source);
		
		Assert.assertNotNull(xmlInvoice);
		Assert.assertTrue(xmlInvoice.contains("invoice"));
		Assert.assertTrue(xmlInvoice.contains("book"));
		
		logger.info(xmlInvoice);

		byte[] htmlInvoice = (byte[]) xslTransformer.transform(xmlInvoice);
		Assert.assertNotNull(htmlInvoice);

		IOUtils.write(htmlInvoice, System.out);
	}

}
