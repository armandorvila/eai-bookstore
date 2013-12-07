package com.armandorv.miw.eai.bookstore.api.service;

import com.armandorv.miw.eai.bookstore.api.domain.Invoice;

/**
 * Service which is in charge of deal with invoices.
 * 
 * @author armandorv
 * 
 */
public interface IInvoiceService {

	void saveInvoice(Invoice invoice);

	Invoice find(long id);

	Invoice findByNumber(String number);
}
