package com.armandorv.miw.eai.bookstore.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BookServiceTest.class, CustomerServiceTest.class,
		InvoiceServiceTest.class, NotificationServiceTest.class,
		ShipmentServiceTest.class })
public class ServiceTests {

}
