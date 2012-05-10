import static org.junit.Assert.*;

import org.junit.Test;

import dataAccess.Session;
import dataAccess.SessionManager;
import dataAccess.SessionManagerFactory;


public class SessionManagerTest {

	@Test
	public void TestSqliteConnection_ShouldConnect() {
		SessionManager sm = SessionManagerFactory.createSessionManager();
		Session s = sm.getSession();
		s.executeNonQuery("create table country (name, currency)");
		s.close();
	}

}
