package com.armandorv.miw.eai.bookstore.impl.component;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.miw.eai.bookstore.api.domain.Shipment;
import com.armandorv.miw.eai.bookstore.api.service.ICustomerService;
import com.armandorv.miw.eai.bookstore.api.service.IShipmentService;

public class VipVerifier {

	private static long ONE_YEAR = 31_557_600_000L;

	@Autowired
	private IShipmentService shipmentService;

	@Autowired
	private ICustomerService customerService;

	private double vip_amount = 2000;

	public Shipment verifyIfVip(Shipment shipment) {

		Date from = new Date(new Date().getTime() - ONE_YEAR);

		List<Shipment> shipments = shipmentService.findShipmentsByCustomer(
				shipment.getCustomer(), from);

		shipment.getCustomer().setVip(shipmentsCost(shipments) > vip_amount);

		customerService.updateCustomer(shipment.getCustomer());

		shipment.setExpress(shipment.getCustomer().isVip());

		return shipment;
	}

	private double shipmentsCost(List<Shipment> shipments) {
		double spentAmount = 0.0;
		for (Shipment pastShipment : shipments) {
			spentAmount = pastShipment.shipmentPrice();
		}
		return spentAmount;
	}

}