package com.armandorv.miw.eai.bookstore.impl.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.armandorv.miw.eai.bookstore.api.domain.Customer;
import com.armandorv.miw.eai.bookstore.api.domain.Invoice;
import com.armandorv.miw.eai.bookstore.api.domain.Shipment;
import com.armandorv.miw.eai.bookstore.api.service.IShipmentService;

@Transactional
@Service
public class ShipmentServiceImpl extends JdbcDaoSupport implements
		IShipmentService {

	private static final RowMapper<Shipment> rowMapper = new ShipmentRowMapper();

	@Value("${bookstore.sql.findShipmentsByCustomerSql}")
	private String findShipmentsByCustomerSql;

	@Autowired
	public ShipmentServiceImpl(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	public List<Shipment> findShipmentsByCustomer(Customer customer, Date from) {
		return super.getJdbcTemplate().query(this.findShipmentsByCustomerSql,
				new Object[] { from, customer.getId() }, rowMapper);
	}

	static class ShipmentRowMapper implements RowMapper<Shipment> {

		@Override
		public Shipment mapRow(ResultSet rs, int count) throws SQLException {
			Shipment shipment = new Shipment();

			shipment.setId(rs.getLong("id"));
			shipment.setDate(rs.getDate("shipment_date"));
			shipment.setExpress(rs.getBoolean("express"));

			Customer customer = new Customer();

			customer.setId(rs.getLong("id"));
			customer.setName(rs.getString("name"));
			customer.setNif(rs.getString("nif"));
			customer.setAddress(rs.getString("address"));
			customer.setDebt(rs.getDouble("debt"));

			shipment.setCustomer(customer);

			Invoice invoice = new Invoice();
			invoice.setId(rs.getLong("id"));
			invoice.setDate(rs.getDate("invoice_date"));
			invoice.setImporte(rs.getDouble("amount"));
			invoice.setNumber(rs.getString("invoice_number"));

			shipment.setInvoice(invoice);

			return shipment;
		}
	}

}