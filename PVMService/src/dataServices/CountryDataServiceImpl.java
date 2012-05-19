package dataServices;

import java.util.*;
import dataAccess.*;
import models.*;

public class CountryDataServiceImpl implements CountryDataService {
	private SessionManager sessionManager;
	
	public CountryDataServiceImpl(SessionManager sessionManager)
	{
		this.sessionManager = sessionManager;
	}

	@Override
	public Country getCountryById(int countryId) {
		List<Coin> coins = new ArrayList();
		Country result = null;
		Session s = sessionManager.getSession();
		QueryResult qr = s.executeQuery("select Name, Value from CountryCoins where CountryID = ?", countryId);
		for(Object obj : qr)
		{
			Map<String, Object> row = (Map<String, Object>)obj;
			coins.add(new Coin((String)row.get("Name"), ((Double)row.get("Value")).floatValue()));
		}
		
		qr.close();
		qr = s.executeQuery("select Name, CurrencyName from Country where ID = ?", countryId);
		for(Object obj : qr)
		{
			Map<String, Object> row = (Map<String, Object>)obj;
			result = new Country(countryId, (String)row.get("Name"), (String)row.get("CurrencyName"), coins.toArray(new Coin[] {}));
			break;
		}
		qr.close();
		s.close();
		return result;
	}

}
