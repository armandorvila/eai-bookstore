package com.armandorv.miw.eai.bookstore.impl.transformer;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import com.armandorv.miw.eai.bookstore.impl.component.CorrlationGroupSize;

public class CorrelationGroupSizeTransformer extends AbstractMessageTransformer {
	
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		message.setCorrelationGroupSize(CorrlationGroupSize.getINSTANCE()
				.getGroupSize());
		return message;
	}
}
