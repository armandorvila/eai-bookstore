package com.armandorv.miw.eai.bookstore.api.service;

import com.armandorv.miw.eai.bookstore.api.domain.Customer;

/**
 * Service intended to deal with customers.
 * 
 * @author armandorv
 * 
 */
public interface ICustomerService {

	void saveCustomer(Customer customer);
	
	void updateCustomer(Customer customer);

	Customer findCusomter(String nif);

}
