package dataServices;

import java.util.Date;
import java.util.Map;

import dataAccess.*;
import models.Order;
import models.PizzaTopping;

public class OrderDataServiceImpl implements OrderDataService {
	private SessionManager sessionManager;
	
	public OrderDataServiceImpl(SessionManager sessionManager)
	{
		this.sessionManager = sessionManager;
	}
	
	public boolean add(Order order)
	{
		Session s = sessionManager.getSession();
		try 
		{
			if (!s.executeNonQuery("insert into Orders (VendorID, BaseID, OrderTime, Amount) values(?, ?, ?, ?)"
					, order.getVendor().getId(), order.getPizzaBase().getId(), order.getOrderTime().getTime(), ((Float)order.getAmount()).doubleValue()))
				throw new Exception();
				
			QueryResult qs = s.executeQuery("SELECT last_insert_rowid() as ID");
			int id = 0;
			for(Object obj : qs)
			{
				Map<String, Object> row = (Map<String, Object>)obj;
				id = (Integer)row.get("ID");
				break;
			}
			qs.close();
			PizzaTopping[] toppings = order.getPizzaToppings();
			for(PizzaTopping t : toppings)
			{
				if (!s.executeNonQuery("insert into ToppingOrders (OrderID, ToppingID, Amount) values(?, ?, ?)", id, t.getId(), ((Float)t.getPrice()).doubleValue()))
					throw new Exception();
			}
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		finally
		{
			s.close();			
		}
	}
}
