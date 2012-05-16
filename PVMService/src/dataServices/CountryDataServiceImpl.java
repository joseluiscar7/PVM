package dataServices;

import java.util.Map;

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
		Country result = null;
		Session s = sessionManager.getSession();
		QueryResult qr = s.executeQuery("select Name, CurrencyName from Country where ID = ?", countryId);
		for(Object obj : qr)
		{
			Map<String, Object> row = (Map<String, Object>)obj;
			result = new Country(countryId, (String)row.get("Name"), (String)row.get("CurrencyName"));
			break;
		}
		qr.close();
		s.close();
		return result;
	}

}
