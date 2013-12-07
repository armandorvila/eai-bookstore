package com.armandorv.miw.eai.bookstore.impl.component;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.armandorv.miw.eai.bookstore.api.domain.Shipment;
import com.armandorv.miw.eai.bookstore.api.service.IShipmentService;

public class ShipmentManager {

	private static long ONE_YEAR = 31_557_600_000L;

	@Autowired
	private IShipmentService shipmentService;

	@Value("${bookstore.rules.vip_amount}")
	private double vip_amount;

	public Shipment manage(Shipment shipment) {

		Date from = new Date(new Date().getTime() - ONE_YEAR);

		List<Shipment> shipments = shipmentService.findShipmentsByCustomer(
				shipment.getCustomer(), from);
		
		shipment.getCustomer().setVip(shipmentsCost(shipments) > vip_amount);

		shipment.setExpress(shipment.getCustomer().isVip());
		shipment.setDate(new Date());
		
		//FIXME Mule catch errors and It never get 
		shipmentService.save(shipment);

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