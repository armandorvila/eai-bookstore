package com.armandorv.miw.eai.bookstore.impl.service.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.armandorv.miw.eai.bookstore.api.domain.Book;

public class BookMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int count) throws SQLException {
		com.armandorv.miw.eai.bookstore.api.domain.Book book = new  Book();
		
		book.setId(rs.getLong("id"));
		
		book.setName(rs.getString("name"));
		book.setDescription(rs.getString("description"));
		book.setIsbn(rs.getString("isbn"));
		
		book.setPrice(rs.getDouble("price"));
		book.setStock(rs.getInt("stock"));
		
		return book;
	}

}
