package com.armandorv.miw.eai.bookstore.impl.service.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementSetter;

public class BookWaitingListPsSetter implements PreparedStatementSetter {

	private Long bookId;
	private Long customerId;

	public BookWaitingListPsSetter(Long bookId, Long customerId) {
		super();
		this.bookId = bookId;
		this.customerId = customerId;
	}

	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		ps.setLong(1, bookId);
		ps.setLong(2, customerId);
	}

}
