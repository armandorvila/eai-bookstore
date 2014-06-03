package com.armandorv.miw.eai.bookstore.flows;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ InStockOrdersWithLoanTest.class,
		InStockOrdersWithoutLoanTest.class, NonStockOrdersTest.class })
public class FlowTests {

}
