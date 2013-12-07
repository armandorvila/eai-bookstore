package com.armandorv.miw.eai.bookstore.api.service;

import com.armandorv.miw.eai.bookstore.api.domain.Notification;

/**
 * Service to manage notifications sent from the book store to their customers.
 * 
 * @author armandorv
 * 
 */
public interface INotificationService {

	/**
	 * Persist a new notification to the data base.
	 * 
	 * @param notificaiton
	 */
	void saveNotification(Notification notificaiton);
}
