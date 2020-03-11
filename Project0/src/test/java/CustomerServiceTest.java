import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Status;
import com.revature.services.AccountService;
import com.revature.services.CustomerService;

public class CustomerServiceTest {
	private CustomerService customerService = null;
	private Customer c = null;
	private Account a = null;
	
	@Before
	public void setUp() throws Exception {
		customerService = new CustomerService();
		c = new Customer("Galo", "Romero", "234", "another1", "123");
		a = new Account(c);
	}
	
	
	@Test
	public void testFindAll() {
		assertNotNull(customerService.findAll());
	}
	
	@Test
	public void testInsert() {
		assertEquals(-1, customerService.insert(c));
	}
	
	@Test
	public void testFindByAccount() {
		assertNotNull(customerService.findByAccount(a));
	}
}
