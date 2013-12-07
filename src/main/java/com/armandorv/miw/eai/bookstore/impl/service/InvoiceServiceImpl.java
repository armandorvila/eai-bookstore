package com.armandorv.miw.eai.bookstore.impl.service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.armandorv.miw.eai.bookstore.api.domain.Invoice;
import com.armandorv.miw.eai.bookstore.api.service.IInvoiceService;

@Transactional
@Service
public class InvoiceServiceImpl extends JdbcDaoSupport implements
		IInvoiceService {

	@Value("${bookstore.sql.saveInvoice}")
	private String saveInvoiceSql;
	
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
	
	private long nextId(){
		return super.getJdbcTemplate().queryForInt(nextId);
	}

}