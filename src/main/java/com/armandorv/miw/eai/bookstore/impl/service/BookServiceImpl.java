package com.armandorv.miw.eai.bookstore.impl.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.armandorv.miw.eai.bookstore.api.domain.Book;
import com.armandorv.miw.eai.bookstore.api.domain.BookWaitingList;
import com.armandorv.miw.eai.bookstore.api.domain.Customer;
import com.armandorv.miw.eai.bookstore.api.service.IBookService;
import com.armandorv.miw.eai.bookstore.impl.service.jdbc.BookMapper;
import com.armandorv.miw.eai.bookstore.impl.service.jdbc.BookPsSetter;
import com.armandorv.miw.eai.bookstore.impl.service.jdbc.BookWaitingListPsSetter;
import com.armandorv.miw.eai.bookstore.impl.service.jdbc.CustomerMapper;

/**
 * <p>
 * Transactional jdbc based implementation of IBookService. It inherit from a
 * Spring JDBC class in order to execute JDBC operations by it self.
 * </p>
 * 
 * <p>
 * Note: It was a design decision avoid another indirection level which had been
 * unavoidable if another DAO Layer was been implemented.
 * </p>
 * 
 * @author armandorv
 * 
 */
@Transactional
@Service
public class BookServiceImpl extends JdbcDaoSupport implements IBookService {

	@Value("${bookstore.sql.bookStock}")
	private String countBookSql;

	@Value("${bookstore.sql.findBookSql}")
	private String findBookSql;

	@Value("${bookstore.sql.findAllBooksSql}")
	private String findAllSql;

	@Value("${bookstore.sql.queryWaitingList}")
	private String queryWaitingList;

	@Value("${bookstore.sql.updateBook}")
	private String updateBook;

	@Value("${bookstore.sql.updateWaitingList}")
	private String updateWaitingList;

	@Autowired
	public BookServiceImpl(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	public boolean isAvailable(Book book) {
		int count = super.getJdbcTemplate().queryForInt(countBookSql,
				book.getIsbn());
		return count >= 1;
	}

	@Override
	public boolean isAvailable(Book book, int amount) {
		int count = super.getJdbcTemplate().queryForInt(countBookSql,
				book.getIsbn());
		return count >= amount;
	}

	@Override
	public Book findBook(String isbn) {
		return super.getJdbcTemplate().queryForObject(findBookSql,
				new Object[] { isbn }, new BookMapper());
	}

	@Override
	public void updateBook(Book book) {
		super.getJdbcTemplate().update(updateBook, new BookPsSetter(book));
	}

	@Override
	public BookWaitingList findWaitingList(Book book) {
		List<Customer> cusomters = super.getJdbcTemplate().query(
				queryWaitingList, new Object[] { book.getIsbn() },
				new CustomerMapper());
		return new BookWaitingList(findBook(book.getIsbn()), cusomters);
	}

	@Override
	public void addToWaitingList(Book book, Customer customer) {
		super.getJdbcTemplate().update(updateWaitingList,
				new BookWaitingListPsSetter(book.getId(), customer.getId()));
	}

	@Override
	public List<Book> findAll() {
		return super.getJdbcTemplate().query(findAllSql, new BookMapper());
	}
}