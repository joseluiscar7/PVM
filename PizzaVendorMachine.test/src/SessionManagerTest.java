import static org.junit.Assert.*;

import org.junit.Test;

import common.Session;
import common.SessionManager;
import common.SessionManagerFactory;


public class SessionManagerTest {

	@Test
	public void TestSqliteConnection_ShouldConnect() {
		SessionManager sm = SessionManagerFactory.createSessionManager();
		Session s = sm.getSession();
		s.executeNonQuery("create table country (name, currency)");
		s.close();
	}

}
