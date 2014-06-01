package com.armandorv.miw.eai.bookstore.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import com.armandorv.miw.eai.bookstore.AbstractServiceTest;
import com.armandorv.miw.eai.bookstore.api.domain.Customer;
import com.armandorv.miw.eai.bookstore.api.domain.Notification;
import com.armandorv.miw.eai.bookstore.api.service.ICustomerService;
import com.armandorv.miw.eai.bookstore.api.service.INotificationService;

@DirtiesContext
public class NotificationServiceTest extends AbstractServiceTest {

	@Autowired
	private INotificationService notificationService;
	
	@Autowired
	private ICustomerService customerService;

	@Test
	public void testSaveNotification() {
		Assert.assertNotNull(notificationService);
		
		Customer armando = customerService.findCusomter(ARMANDO_NIF);
		
		Notification notification = new Notification();
		
		notification.setCustomer(armando);
		notification.setSubject("Order refused .");
		notification.setMessage("Your order bla bla has been denied by not stock");
		
		notificationService.saveNotification(notification);
	}
}
