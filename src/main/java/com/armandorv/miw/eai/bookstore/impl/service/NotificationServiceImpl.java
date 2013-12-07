package com.armandorv.miw.eai.bookstore.impl.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.armandorv.miw.eai.bookstore.api.domain.Notification;
import com.armandorv.miw.eai.bookstore.api.service.INotificationService;

@Transactional
@Service
public class NotificationServiceImpl extends JdbcDaoSupport implements
		INotificationService {

	@Value("${bookstore.sql.saveNotification}")
	private String saveNotificationSql;

	@Value("${bookstore.sql.nextId}")
	private String nextId;

	@Autowired
	public NotificationServiceImpl(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	public void saveNotification(final Notification notificaiton) {
		super.getJdbcTemplate().update(saveNotificationSql,
				new PreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps)
							throws SQLException {
						ps.setLong(1, nextId());
						ps.setString(2, notificaiton.getSubject());
						ps.setString(3, notificaiton.getMessage());
						ps.setLong(4, notificaiton.getCustomer().getId());
					}
				});

	}

	private long nextId() {
		return super.getJdbcTemplate().queryForInt(nextId);
	}

}
