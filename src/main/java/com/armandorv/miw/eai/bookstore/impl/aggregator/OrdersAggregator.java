package com.armandorv.miw.eai.bookstore.impl.aggregator;

import java.util.Iterator;
import java.util.UUID;

import org.mule.DefaultMuleEvent;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.store.ObjectStoreException;
import org.mule.api.transformer.TransformerException;
import org.mule.routing.AbstractAggregator;
import org.mule.routing.AggregationException;
import org.mule.routing.EventGroup;
import org.mule.routing.correlation.CollectionCorrelatorCallback;
import org.mule.routing.correlation.EventCorrelatorCallback;

import com.armandorv.miw.eai.bookstore.api.domain.Order;
import com.armandorv.miw.eai.bookstore.api.domain.Shipment;
import com.armandorv.miw.eai.bookstore.impl.component.CorrlationGroupSize;

/**
 * It aggregates orders by their correlation id (parent AbstractAggregator
 * carries out this).
 * 
 * @author armandorv
 * 
 */
public class OrdersAggregator extends AbstractAggregator {

	@Override
	protected EventCorrelatorCallback getCorrelatorCallback(
			final MuleContext muleContext) {

		return new CollectionCorrelatorCallback(muleContext, false, storePrefix) {

			@Override
			public MuleEvent aggregateEvents(EventGroup events)
					throws AggregationException {

				Shipment shipment = new Shipment();
				shipment.setShipmentNumber(UUID.randomUUID().toString());

				try {
					for (Iterator<MuleEvent> iterator = events.iterator(); iterator
							.hasNext();) {
						try {
							shipment.addOrder(iterator.next().getMessage()
									.getPayload(Order.class));
						} catch (TransformerException e) {
							throw new AggregationException(events, null, e);
						}
					}

				} catch (ObjectStoreException e) {
					throw new AggregationException(events, null, e);
				}

				shipment.setCustomer(shipment.getOrders().iterator().next()
						.getCustomer());

				CorrlationGroupSize.getINSTANCE().reset();

				return new DefaultMuleEvent(new DefaultMuleMessage(shipment,
						muleContext), events.getMessageCollectionEvent());
			}
		};
	}
}
