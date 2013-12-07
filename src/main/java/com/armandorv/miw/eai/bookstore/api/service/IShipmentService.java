package com.armandorv.miw.eai.bookstore.api.service;

import java.util.Date;
import java.util.List;

import com.armandorv.miw.eai.bookstore.api.domain.Customer;
import com.armandorv.miw.eai.bookstore.api.domain.Shipment;

/**
 * Service intended to manage shipment entities of the book store.
 * 
 * @author armandorv
 * 
 */
public interface IShipmentService {
	
	/**
	 * Find all shipments of a Customer given a date which we want retrieve it from.
	 * 
	 * @param customer
	 * @param from
	 * @return
	 */
	List<Shipment> findShipmentsByCustomer(Customer customer, Date from);

	void save(Shipment shipment);
}
