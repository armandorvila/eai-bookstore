package com.armandorv.miw.eai.bookstore;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:META-INF/spring/bookstore-context.xml",
		"classpath:META-INF/spring/bookstore-datasource-context-test.xml" })
public abstract class AbstractServiceTest {

	protected static final String ARMANDO_NIF = "62789475D";
	protected static final String JULIO_NIF = "65459475D";
}
