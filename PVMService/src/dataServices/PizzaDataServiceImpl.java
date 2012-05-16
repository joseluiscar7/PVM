package dataServices;

import java.util.Map;

import models.Country;
import models.PizzaBase;
import models.PizzaTopping;
import dataAccess.QueryResult;
import dataAccess.Session;
import dataAccess.SessionManager;

public class PizzaDataServiceImpl implements PizzaDataService {
	private SessionManager sessionManager;
	private PizzaInfoDataService pizzaInfoDataService;
	
	public PizzaDataServiceImpl(SessionManager sessionManager, PizzaInfoDataService pizzaInfoDataService)
	{
		this.sessionManager = sessionManager;
		this.pizzaInfoDataService = pizzaInfoDataService;
	}

	@Override
	public PizzaBase getPizzaBaseById(int baseId, Country country) {
		float price = 0, tax = 0;
		String baseName = null;
		Session s = sessionManager.getSession();
		QueryResult qr = s.executeQuery("select BaseName, BasePrice, Tax from CountryBase where CountryID = ? and BaseID = ?", country.getId(), baseId);
		for(Object obj : qr)
		{
			Map<String, Object> row = (Map<String, Object>)obj;
			baseName = (String)row.get("BaseName");
			price = ((Double)row.get("BasePrice")).floatValue();
			tax = ((Double)row.get("Tax")).floatValue();
			break;
		}
		qr.close();
		s.close();	
		return new PizzaBase(baseId, country, baseName, price, tax, pizzaInfoDataService.getPizzaBaseCookTime(baseId));
	}

	@Override
	public PizzaTopping getPizzaToppingById(int toppingId, Country country) {
		float price = 0, tax = 0;
		String toppingName = null;
		Session s = sessionManager.getSession();
		QueryResult qr = s.executeQuery("select ToppingName, ToppingPrice, Tax from CountryTopping where CountryID = ? and ToppingID = ?", country.getId(), toppingId);
		for(Object obj : qr)
		{
			Map<String, Object> row = (Map<String, Object>)obj;
			toppingName = (String)row.get("ToppingName");
			price = ((Double)row.get("ToppingPrice")).floatValue();
			tax = ((Double)row.get("Tax")).floatValue();
			break;
		}
		qr.close();
		s.close();	
		return new PizzaTopping(toppingId, country, toppingName, price, tax);
	}
}
