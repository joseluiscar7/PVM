package common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class QueryResult implements Iterable {
	private ResultSet rs;
	private PreparedStatement ps;
	
	public void close()
	{
		try {
			if (rs != null) rs.close();
		} catch(Exception e) {}
		
		try {
			if (ps != null) ps.close();
		} catch(Exception e) {}
	}
	
	QueryResult(PreparedStatement ps, ResultSet rs)
	{
		this.ps = ps;
		this.rs = rs;
	}

	public Iterator<Map<String, Object>> iterator() {
		return new Iterator<Map<String, Object>>(){

			public boolean hasNext() {
				try {
					return rs.next();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return false;
			}

			public Map<String, Object> next() {
				Map map = new HashMap();
				ResultSetMetaData metaData;
				try {
					metaData = rs.getMetaData();
					int length = metaData.getColumnCount();
					
					for(int i = 1; i <= length; i++)
					{
						map.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				return map;
			}

			public void remove() {
			}
		};
	}
	
}
