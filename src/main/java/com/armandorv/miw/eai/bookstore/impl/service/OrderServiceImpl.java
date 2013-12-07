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

import com.armandorv.miw.eai.bookstore.api.domain.Order;
import com.armandorv.miw.eai.bookstore.api.service.IOrderService;

@Service
@Transactional
public class OrderServiceImpl extends JdbcDaoSupport implements IOrderService {

	@Value("${bookstore.sql.nextId}")
	private String nextId;

	@Value("${bookstore.sql.saveOrder}")
	private String saveOrder;
	
	@Autowired
	public OrderServiceImpl(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	@Override
	public void saveOrder(final Order order) {
		super.getJdbcTemplate().update(saveOrder,
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps)
							throws SQLException {

						ps.setLong(1, nextId());
						ps.setDate(2, new Date(order.getDate().getTime()));
						ps.setLong(3, order.getBook().getId());
						ps.setInt(4, order.getAmount());
						ps.setLong(5, order.getCustomer().getId());
					}
				});

	}

	private long nextId() {
		return super.getJdbcTemplate().queryForInt(nextId);
	}

}
