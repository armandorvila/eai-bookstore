package com.armandorv.miw.eai.bookstore.impl.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.armandorv.miw.eai.bookstore.api.domain.Customer;
import com.armandorv.miw.eai.bookstore.api.service.ICustomerService;
import com.armandorv.miw.eai.bookstore.impl.service.jdbc.CustomerMapper;

@Transactional
@Service
public class CusomterServiceImpl extends JdbcDaoSupport implements
		ICustomerService {

	@Value("${bookstore.sql.findCustomerByNif}")
	private String findCustomerByNif;

	@Value("${bookstore.sql.updateCustomer}")
	private String updateCustomer;

	@Value("${bookstore.sql.saveCustomer}")
	private String saveCustomer;

	@Value("${bookstore.sql.nextId}")
	private String nextId;

	@Autowired
	public CusomterServiceImpl(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	public void updateCustomer(final Customer customer) {
		super.getJdbcTemplate().update(updateCustomer,
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps)
							throws SQLException {

						ps.setString(1, customer.getName());
						ps.setString(2, customer.getNif());
						ps.setString(3, customer.getAddress());
						ps.setString(4, customer.getMail());
						ps.setDouble(5, customer.getDebt());

						ps.setLong(6, customer.getId());
					}
				});
	}

	@Override
	public Customer findCusomter(String nif) {
		List<Customer> result = super.getJdbcTemplate().query(
				findCustomerByNif, new Object[] { nif }, new CustomerMapper());

		if (result.isEmpty()) {
			return null;
		}
		if (result.size() > 1) {
			throw new RuntimeException(
					"There is more of one customer with the same nif");
		}
		return result.get(0);
	}

	@Override
	public void saveCustomer(final Customer customer) {
		super.getJdbcTemplate().update(saveCustomer,
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps)
							throws SQLException {

						ps.setLong(1, nextId());
						ps.setString(2, customer.getName());
						ps.setString(3, customer.getNif());
						ps.setString(4, customer.getAddress());
						ps.setString(5, customer.getMail());
						ps.setDouble(6, customer.getDebt());
					}
				});
	}

	private long nextId() {
		return super.getJdbcTemplate().queryForInt(nextId);
	}

}