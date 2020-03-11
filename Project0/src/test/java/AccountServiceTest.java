import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.models.Account;
import com.revature.models.Status;
import com.revature.services.AccountService;

public class AccountServiceTest {
	private AccountService accountService = null;
	private Account a = null;
	
	@Before
	public void setUp() throws Exception {
		accountService = new AccountService();
		a = new Account(3, 0, Status.PENDING);
	}
	
	@Test
	public void testfindAll() {
		assertNotNull(accountService.findAll());
	}
	
	@Test
	public void testInsert() {
		assertEquals(7, accountService.insert(a));
	}
	
	@Test
	public void testdeposit() {
		assertFalse(accountService.deposit(a));
	}
	
	@Test
	public void testWithdraw() {
		assertFalse(accountService.withdraw(a));
		
	}
	
	@Test
	public void testChangeStatus() {
		assertFalse(accountService.changeStatus(a));
	}
	
	@Test
	public void testDelete() {
		assertFalse(accountService.delete(a));
		
	}
}
