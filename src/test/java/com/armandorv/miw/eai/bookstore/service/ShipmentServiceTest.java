package com.armandorv.miw.eai.bookstore.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.miw.eai.bookstore.AbstractServiceTest;
import com.armandorv.miw.eai.bookstore.api.domain.Customer;
import com.armandorv.miw.eai.bookstore.api.domain.Shipment;
import com.armandorv.miw.eai.bookstore.api.service.ICustomerService;
import com.armandorv.miw.eai.bookstore.api.service.IInvoiceService;
import com.armandorv.miw.eai.bookstore.api.service.IShipmentService;

public class ShipmentServiceTest extends AbstractServiceTest {

	private Date from;
	
	@Autowired
	private IShipmentService shipmentService;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IInvoiceService invoiceService;

	private Customer armando;

	@Before
	public void setUp() {
		from = new Date(new Date().getTime() - 100000000L);
		armando = customerService.findCusomter(ARMANDO_NIF);
	}

	@Test
	public void testSave(){
		Shipment shipment = new Shipment();
		shipment.setShipmentNumber(UUID.randomUUID().toString());
		shipment.setDate(new Date());
		shipment.setExpress(false);
		shipment.setCustomer(armando);
		
		shipment.setInvoice(invoiceService.find(1000L));
		
		List<Shipment> shipments = shipmentService.findShipmentsByCustomer(armando, from);
		int before = shipments.size();
		
		shipmentService.save(shipment);
		
		shipments = shipmentService.findShipmentsByCustomer(armando, from);
		
		int after = shipments.size();
		
		Assert.assertEquals(before + 1, after);
	}
	
	@Test
	public void testFindShipments() {
		Assert.assertNotNull(shipmentService);
		List<Shipment> shipments = shipmentService.findShipmentsByCustomer(armando, from);
		Assert.assertNotNull(shipments);
	}
}
