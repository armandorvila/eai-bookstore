package com.armandorv.miw.eai.bookstore.impl.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.armandorv.miw.eai.bookstore.api.domain.Customer;
import com.armandorv.miw.eai.bookstore.api.domain.Order;
import com.armandorv.miw.eai.bookstore.api.domain.Shipment;
import com.armandorv.miw.eai.bookstore.api.service.ICustomerService;

/**
 * Check if the customer is able to receive the asked loan.
 * 
 * @author armandorv
 * 
 */
public class LoanBroker {

	@Value("${bookstore.rules.allowed_debt}")
	private double allowedDebt;

	@Autowired
	private ICustomerService customerService;

	public Shipment officeLoan(Shipment shipment) {
		Customer customer = customerService.findCusomter(shipment.getCustomer()
				.getNif());

		double loanAmount = loanAmount(shipment);

		if (customer.getDebt() + loanAmount < allowedDebt) {
			customer.setLoanAccepted(true);
			customer.addDebt(loanAmount);
		}

		customerService.updateCustomer(customer);
		shipment.setCustomer(customer);

		return shipment;
	}

	private double loanAmount(Shipment shipment) {
		double importe = 0.0;

		for (Order order : shipment.getOrders()) {
			importe += order.getBook().getPrice() * order.getAmount();
		}
		return importe;
	}
}
