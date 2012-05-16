package dataServices;

import dataAccess.Session;
import dataAccess.SessionManager;
import models.Order;

public class OrderDataServiceImpl implements OrderDataService {
	private SessionManager sessionManager;
	
	public OrderDataServiceImpl(SessionManager sessionManager)
	{
		this.sessionManager = sessionManager;
	}
	
	public Order add(Order order)
	{
		Session s = sessionManager.getSession();
		//s.executeNonQuery(sql, objects);
		s.close();
		return order;
	}
}
