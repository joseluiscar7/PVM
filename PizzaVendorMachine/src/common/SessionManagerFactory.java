package common;

public class SessionManagerFactory {
	public static SessionManager createSessionManager()
	{
		return new SessionManager("org.sqlite.JDBC", "jdbc:sqlite:test.db");
	}
}
