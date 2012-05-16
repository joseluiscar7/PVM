package dataServices;

import java.util.*;

import dataAccess.*;
import models.BaseStock;
import models.Order;
import models.ToppingStock;

public class StockDataServiceImpl implements StockDataService {
	private SessionManager sessionManager;
	private PizzaDataService pizzaDataService;
	public StockDataServiceImpl(SessionManager sessionManager, PizzaDataService pizzaDataService)
	{
		this.sessionManager = sessionManager;
		this.pizzaDataService = pizzaDataService;
	}

	@Override
	public void saveAmount(BaseStock t) {
		Session s = sessionManager.getSession();
		s.executeNonQuery("update VendorStock set amount = ? where VendorID = ? and BaseID = ?", t.getCount(), t.getVendorId(), t.getBase().getId());
		s.close();		
	}

	@Override
	public void saveAmount(ToppingStock t) {
		Session s = sessionManager.getSession();
		s.executeNonQuery("update VendorStock set amount = ? where VendorID = ? and ToppingID = ?", t.getCount(), t.getVendorId(), t.getTopping().getId());
		s.close();	
	}

	@Override
	public List<BaseStock> getStockBases(int vendorId, int countryId) {
		List<BaseStock> result = new ArrayList<BaseStock>();
		Session s = sessionManager.getSession();
		QueryResult qr = s.executeQuery("select BaseID, Count from VendorStock where VendorID = ? and Count > 0 and BaseID > 0", vendorId);
		for(Object row : qr)
		{
			int baseId = (Integer)((Map<String, Object>) row).get("BaseID");
			
			int count = (Integer)((Map<String, Object>) row).get("Count");
			BaseStock b = new BaseStock(vendorId, pizzaDataService.getPizzaBaseById(baseId, countryId), count);
			result.add(b);
		}
		qr.close();
		s.close();	
		return result;
	}

	@Override
	public List<ToppingStock> getStockToppings(int vendorId, int countryId) {
		List<ToppingStock> result = new ArrayList<ToppingStock>();
		Session s = sessionManager.getSession();
		QueryResult qr = s.executeQuery("select ToppingID, Count from VendorStock where VendorID = ? and Count > 0 and ToppingID > 0", vendorId);
		for(Object row : qr)
		{
			int toppingId = (Integer)((Map<String, Object>) row).get("ToppingID");
			
			int count = (Integer)((Map<String, Object>) row).get("Count");
			ToppingStock b = new ToppingStock(vendorId, pizzaDataService.getPizzaToppingById(toppingId, countryId), count);
			result.add(b);
		}
		qr.close();
		s.close();	
		return result;
	}
}
