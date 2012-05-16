package dataServices;

import java.util.*;

import dataAccess.*;
import models.*;

public class VendorDataServiceImpl implements VendorDataService {
	private SessionManager sessionManager;
	private PizzaDataService pizzaDataService;
	private CountryDataService countryDataService;
	private StockDataService stockDataService;
	
	public VendorDataServiceImpl(SessionManager sessionManager, CountryDataService countryDataService, StockDataService stockDataService)
	{
		this.sessionManager = sessionManager;
		this.countryDataService = countryDataService;
		this.stockDataService = stockDataService;
	}
	
	@Override
	public Vendor getById(int id) {
		int countryId = 0;
		String address = null;
		Session s = sessionManager.getSession();
		QueryResult qr = s.executeQuery("select CountryID, Address from Vendor where ID = ?", id);
		for(Object obj : qr)
		{
			Map<String, Object> row = (Map<String, Object>)obj;
			countryId = (Integer)row.get("CountryID");
			address = (String)row.get("Address");
			break;
		}
		qr.close();
		s.close();
		Country country = countryDataService.getCountryById(countryId);
		return new Vendor(id, country, address, stockDataService.getStockBases(id, country), stockDataService.getStockToppings(id, country));
	}

}
