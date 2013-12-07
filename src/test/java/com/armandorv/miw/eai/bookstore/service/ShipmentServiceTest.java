package com.armandorv.miw.eai.bookstore.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.miw.eai.bookstore.AbstractServiceTest;
import com.armandorv.miw.eai.bookstore.api.domain.Customer;
import com.armandorv.miw.eai.bookstore.api.service.ICustomerService;
import com.armandorv.miw.eai.bookstore.api.service.IShipmentService;

public class ShipmentServiceTest extends AbstractServiceTest {

	@Autowired
	private IShipmentService shipmentService;
	
	@Autowired
	private ICustomerService customerService;

	private Customer armando;

	@Before
	public void setUp() {
		armando = customerService.findCusomter(ARMANDO_NIF);
	}

	@Test
	public void testFindShipments() {
		Assert.assertNotNull(shipmentService);
		
		Date from = new Date(new Date().getTime() - 100000000L);
		shipmentService.findShipmentsByCustomer(armando, from);
	}
}
