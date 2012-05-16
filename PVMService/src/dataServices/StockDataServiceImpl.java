package dataServices;

import java.util.*;

import dataAccess.*;
import models.BaseStock;
import models.Country;
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

	private class IdCount
	{
		private int id;
		private int count;
		public IdCount(int id, int count)
		{
			this.id = id;
			this.count = count;
		}
	}
	
	@Override
	public List<BaseStock> getStockBases(int vendorId, Country country) {
		Session s = sessionManager.getSession();
		QueryResult qr = s.executeQuery("select BaseID, Count from VendorStock where VendorID = ? and Count > 0 and BaseID > 0", vendorId);
		List<IdCount> idCounts = new ArrayList<IdCount>();
		for(Object obj : qr)
		{
			Map<String, Object> row = (Map<String, Object>)obj;
			idCounts.add(new IdCount((Integer)row.get("BaseID"), (Integer)row.get("Count")));
		}
		qr.close();
		s.close();	
		List<BaseStock> result = new ArrayList<BaseStock>();
		for(IdCount idcount : idCounts)
		{
			result.add(new BaseStock(vendorId, pizzaDataService.getPizzaBaseById(idcount.id, country), idcount.count));
		}
		return result;
	}

	@Override
	public List<ToppingStock> getStockToppings(int vendorId, Country country) {
		Session s = sessionManager.getSession();
		QueryResult qr = s.executeQuery("select ToppingID, Count from VendorStock where VendorID = ? and Count > 0 and ToppingID > 0", vendorId);
		List<IdCount> idCounts = new ArrayList<IdCount>();
		for(Object obj : qr)
		{
			Map<String, Object> row = (Map<String, Object>)obj;
			idCounts.add(new IdCount((Integer)row.get("ToppingID"), (Integer)row.get("Count")));
		}
		qr.close();
		s.close();
		List<ToppingStock> result = new ArrayList<ToppingStock>();
		for(IdCount idcount : idCounts)
		{
			result.add(new ToppingStock(vendorId, pizzaDataService.getPizzaToppingById(idcount.id, country), idcount.count));
		}
		return result;
	}
}
