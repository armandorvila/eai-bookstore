package com.armandorv.miw.eai.bookstore.impl.service.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementSetter;

import com.armandorv.miw.eai.bookstore.api.domain.Book;

/**
 * set name=? , description=? ,isbn = ? , price = ?, stock=? where id = ?
 * 
 * @author armandorv
 * 
 */
public class BookPsSetter implements PreparedStatementSetter {

	private com.armandorv.miw.eai.bookstore.api.domain.Book book;

	public BookPsSetter(Book book) {
		super();
		this.book = book;
	}

	@Override
	public void setValues(PreparedStatement ps) throws SQLException {

		ps.setString(1, book.getName());
		ps.setString(2, book.getDescription());
		ps.setString(3, book.getIsbn());

		ps.setDouble(4, book.getPrice());
		ps.setInt(5, book.getStock());

		ps.setLong(6, book.getId());
	}

}
