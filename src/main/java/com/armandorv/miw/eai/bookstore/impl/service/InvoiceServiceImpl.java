package com.armandorv.miw.eai.bookstore.impl.service;

import java.sql.Date;
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

import com.armandorv.miw.eai.bookstore.api.domain.Invoice;
import com.armandorv.miw.eai.bookstore.api.service.IInvoiceService;
import com.armandorv.miw.eai.bookstore.impl.service.jdbc.InvoiceMapper;

@Transactional
@Service
public class InvoiceServiceImpl extends JdbcDaoSupport implements
		IInvoiceService {

	@Value("${bookstore.sql.saveInvoice}")
	private String saveInvoiceSql;

	@Value("${bookstore.sql.findInvoiceById}")
	private String findInvoiceById;

	@Value("${bookstore.sql.findInvoiceByNumber}")
	private String findInvoiceByNumber;

	@Value("${bookstore.sql.nextId}")
	private String nextId;

	@Autowired
	public InvoiceServiceImpl(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	public void saveInvoice(final Invoice invoice) {
		super.getJdbcTemplate().update(saveInvoiceSql,
				new PreparedStatementSetter() {

					public void setValues(PreparedStatement ps)
							throws SQLException {
						ps.setLong(1, nextId());
						ps.setDate(2, new Date(invoice.getDate().getTime()));
						ps.setDouble(3, invoice.getImporte());
						ps.setString(4, invoice.getNumber());
					}
				});
	}

	private long nextId() {
		return super.getJdbcTemplate().queryForInt(nextId);
	}

	@Override
	public Invoice find(long id) {
		List<Invoice> result = super.getJdbcTemplate().query(findInvoiceById,
				new Object[] { id }, new InvoiceMapper());

		if (result.isEmpty()) {
			return null;
		}
		if (result.size() > 1) {
			throw new RuntimeException(
					"There is more of one invoice with the same nif");
		}
		return result.get(0);
	}

	@Override
	public Invoice findByNumber(String number) {
		List<Invoice> result = super.getJdbcTemplate().query(
				findInvoiceByNumber, new Object[] { number },
				new InvoiceMapper());

		if (result.isEmpty()) {
			return null;
		}
		if (result.size() > 1) {
			throw new RuntimeException(
					"There is more of one invoice with the same number");
		}
		return result.get(0);
	}

}