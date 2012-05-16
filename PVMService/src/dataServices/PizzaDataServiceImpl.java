package dataServices;

import java.util.Map;

import models.PizzaBase;
import models.PizzaTopping;
import dataAccess.QueryResult;
import dataAccess.Session;
import dataAccess.SessionManager;

public class PizzaDataServiceImpl implements PizzaDataService {
	private SessionManager sessionManager;
	private CountryDataService countryDataService;
	private PizzaInfoDataService pizzaInfoDataService;
	
	public PizzaDataServiceImpl(SessionManager sessionManager, CountryDataService countryDataService, PizzaInfoDataService pizzaInfoDataService)
	{
		this.sessionManager = sessionManager;
		this.countryDataService = countryDataService;
		this.pizzaInfoDataService = pizzaInfoDataService;
	}

	@Override
	public PizzaBase getPizzaBaseById(int baseId, int countryId) {
		PizzaBase result = null;
		Session s = sessionManager.getSession();
		QueryResult qr = s.executeQuery("select BaseName, BasePrice, Tax from CountryBase where CountryID = ? and BaseID = ?", countryId, baseId);
		for(Object obj : qr)
		{
			Map<String, Object> row = (Map<String, Object>)obj;
			String baseName = (String)row.get("BaseName");
			float price = (Float)row.get("BasePrice");
			float tax = (Float)row.get("Tax");
			
			result = new PizzaBase(baseId, countryDataService.getCountryById(countryId), baseName, price, tax, pizzaInfoDataService.getPizzaBaseCookTime(baseId));
			break;
		}
		qr.close();
		s.close();	
		return result;
	}

	@Override
	public PizzaTopping getPizzaToppingById(int toppingId, int countryId) {
		PizzaTopping result = null;
		Session s = sessionManager.getSession();
		QueryResult qr = s.executeQuery("select ToppingName, ToppingPrice, Tax from CountryTopping where CountryID = ? and ToppingID = ?", countryId, toppingId);
		for(Object obj : qr)
		{
			Map<String, Object> row = (Map<String, Object>)obj;
			String toppingName = (String)row.get("ToppingName");
			float price = (Float)row.get("ToppingPrice");
			float tax = (Float)row.get("Tax");
			
			result = new PizzaTopping(toppingId, countryDataService.getCountryById(countryId), toppingName, price, tax);
			break;
		}
		qr.close();
		s.close();	
		return result;
	}
}
