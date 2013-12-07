package com.armandorv.miw.eai.bookstore.impl.component;

import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.miw.eai.bookstore.api.domain.Notification;
import com.armandorv.miw.eai.bookstore.api.domain.Shipment;
import com.armandorv.miw.eai.bookstore.api.service.INotificationService;

public class LoanNotifier {

	@Autowired
	private INotificationService notificationService;

	public Notification loanAcceptedNotification(Shipment shipment) {

		Notification notification = new Notification();
		notification.setCustomer(shipment.getCustomer());

		notification.setSubject("Loan acepted");
		notification.setMessage("Your loan request for "
				+ shipment.shipmentPrice() + " $ has been accepted.");
		notificationService.saveNotification(notification);

		return notification;
	}
}
