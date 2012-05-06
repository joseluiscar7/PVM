package common;

import java.sql.*;
import java.util.ArrayList;

import util.Pool;
import util.PoolDelegate;
import util.Pool.PoolCloseEvent;;

public class SessionManager {
	Pool pool;
	SessionManager(String className, final String connectionString)
	{
		try {
			Class.forName(className).newInstance(); 
		} catch (Exception e) {
			System.out.println("Cannot initialize SessionManager");
			System.exit(-1);
		}
		
		pool = new Pool(new PoolDelegate() {

			public Object createItem(PoolCloseEvent event) {
				try {
					return new Session(DriverManager.getConnection(connectionString), event);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}

			public void destroyItem(Object item) {
				try {
					((Session)item).getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}}, 1, 20);
		
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				pool.close();
			}
		}));
	}

	public Session getSession()
	{
		return (Session)pool.get();
	}
}
