package dataServices;

import java.util.Map;

import models.Country;
import dataAccess.QueryResult;
import dataAccess.Session;
import dataAccess.SessionManager;

public class PizzaInfoDataServiceImpl implements PizzaInfoDataService {
	private SessionManager sessionManager;
	
	public PizzaInfoDataServiceImpl(SessionManager sessionManager)
	{
		this.sessionManager = sessionManager;
	}
	
	@Override
	public int getPizzaBaseCookTime(int baseId) {
		int result = 0;
		Session s = sessionManager.getSession();
		QueryResult qr = s.executeQuery("select CookMinutes from Base where ID = ?", baseId);
		for(Object obj : qr)
		{
			Map<String, Object> row = (Map<String, Object>)obj;
			result = (Integer)row.get("CookMinutes");
			break;
		}
		qr.close();
		s.close();
		return result;
	}

}
