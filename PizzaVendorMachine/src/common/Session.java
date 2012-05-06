package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Pool.PoolCloseEvent;

public class Session {
	private Connection conn;
	private PoolCloseEvent event;
	
	public Session(Connection conn, PoolCloseEvent event)
	{
		this.conn = conn;
		this.event = event;
	}

	public boolean executeNonQuery(String sql, Object...objects)
	{
		PreparedStatement s = null;
		try {
			s = conn.prepareStatement(sql);
			int i = 0;
			for(Object o : objects)
			{
				s.setObject(++i, o);
			}
			s.executeUpdate();
//			conn.commit();
			s.close();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (s != null)
				try { 
					s.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
	
	public QueryResult executeQuery(String sql, Object...objects)
	{
		PreparedStatement s;
		try {
			s = conn.prepareStatement(sql);
			int i = 0;
			for(Object o : objects)
			{
				s.setObject(++i, o);
			}
			ResultSet result = s.executeQuery();
			return new QueryResult(s, result);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	public void close()
	{
		event.raiseEvent();
	}
	
	Connection getConnection()
	{
		return conn;
	}

}
