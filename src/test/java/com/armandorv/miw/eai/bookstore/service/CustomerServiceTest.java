package com.armandorv.miw.eai.bookstore.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.miw.eai.bookstore.AbstractServiceTest;
import com.armandorv.miw.eai.bookstore.api.domain.Customer;
import com.armandorv.miw.eai.bookstore.api.service.ICustomerService;

public class CustomerServiceTest extends AbstractServiceTest {

	@Autowired
	private ICustomerService customerService;
	
	@Test
	public void testFindCustomer() {
		Assert.assertNotNull(customerService);
		
		Customer armando = customerService.findCusomter(ARMANDO_NIF);
		Assert.assertNotNull(armando);
		Assert.assertEquals(ARMANDO_NIF, armando.getNif());
		
		Customer julio = customerService.findCusomter(JULIO_NIF);
		Assert.assertNotNull(julio);
		Assert.assertEquals(JULIO_NIF, julio.getNif());
	}
	
	@Test
	public void testSaveCustomer(){
		Assert.assertNotNull(customerService);
		
		Customer pepe = new Customer("Pepe", "87872", "Madrid");
		pepe.setDebt(2);
		pepe.setMail("pepe@yolovalgo.com");
		
		customerService.saveCustomer(pepe);
	}
	
	@Test
	public void updateCustomerTest(){
		Assert.assertNotNull(customerService);
		
		Customer armando = customerService.findCusomter(ARMANDO_NIF);
		Assert.assertNotNull(armando);
		
		String newAddress = armando.getAddress() + " Spain";
		armando.setAddress(newAddress);
		
		customerService.updateCustomer(armando);
		armando = customerService.findCusomter(ARMANDO_NIF);
		Assert.assertNotNull(armando);
		Assert.assertEquals(newAddress, armando.getAddress());
	}
}
