package com.armandorv.miw.eai.bookstore.impl.service.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.armandorv.miw.eai.bookstore.api.domain.Customer;

public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
		Customer customer = new Customer();

		customer.setId(rs.getLong("id"));
		customer.setNif(rs.getString("nif"));
		customer.setName(rs.getString("name"));
		customer.setMail(rs.getString("mail"));
		customer.setAddress(rs.getString("address"));
		customer.setDebt(rs.getDouble("debt"));

		return customer;
	}
}
