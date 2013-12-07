package com.armandorv.miw.eai.bookstore.impl.service.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.armandorv.miw.eai.bookstore.api.domain.Invoice;

public class InvoiceMapper implements RowMapper<Invoice> {

	@Override
	public Invoice mapRow(ResultSet rs, int arg1) throws SQLException {
		Invoice invoice = new Invoice();

		invoice.setId(rs.getLong("id"));
		invoice.setDate(rs.getDate("date"));
		invoice.setImporte(rs.getDouble("amount"));
		invoice.setNumber(rs.getString("number"));

		return invoice;
	}
}
