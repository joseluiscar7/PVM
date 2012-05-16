package services;

import java.util.List;

import dataServices.OrderDataService;
import dataServices.StockDataService;
import dataServices.VendorDataService;

import models.BaseStock;
import models.Order;
import models.ToppingStock;
import models.Vendor;

public class PizzaServiceImpl implements PizzaService {
	private OrderDataService orderDataService;
	private StockDataService stockDataService;
	private VendorDataService vendorDataService;

	public PizzaServiceImpl(OrderDataService orderDataService, StockDataService stockDataService, VendorDataService vendorDataService)
	{
		this.orderDataService = orderDataService;
		this.stockDataService = stockDataService;
		this.vendorDataService = vendorDataService;
	}

	@Override
	public Vendor getVendorById(int id) {
		return vendorDataService.getById(id);
	}

	@Override
	public Order placeOrder(Order order) {
		List<BaseStock> baseStockList = order.getVendor().getBaseStockList();
		List<ToppingStock> toppingStockList = order.getVendor().getToppingStockList();
		
		
		Order ret = orderDataService.add(order);
		
		BaseStock b = null;
		ToppingStock t = null;
		for(BaseStock item : baseStockList)
		{
			if (item.getBase().equals(order.getPizzaBase()))
			{
				b = item;
				break;
			}
		}
		if (b == null || b.getCount() == 0)
			return null;
		for(ToppingStock item : toppingStockList)
		{
			if (item.getTopping().equals(order.getPizzaTopping()))
			{
				t = item;
				break;
			}
		}
		if (t == null || t.getCount() == 0)
			return null;
		
		b.setCount(b.getCount() - 1);
		t.setCount(t.getCount() - 1);
		
		stockDataService.saveAmount(b);
		stockDataService.saveAmount(t);
		return ret;
	}
	
}
