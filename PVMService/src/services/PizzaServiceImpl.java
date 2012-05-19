package services;

import java.util.List;

import dataServices.OrderDataService;
import dataServices.StockDataService;
import dataServices.VendorDataService;

import models.BaseStock;
import models.Order;
import models.PizzaTopping;
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
	public boolean placeOrder(Order order) {
		List<BaseStock> baseStockList = order.getVendor().getBaseStockList();
		List<ToppingStock> toppingStockList = order.getVendor().getToppingStockList();
		
		
		if (!orderDataService.add(order))
			return false;
		
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
			return false;
		
		b.setCount(b.getCount() - 1);
		stockDataService.saveAmount(b);
		
		for(ToppingStock item : toppingStockList)
		{
			PizzaTopping[] toppings = order.getPizzaToppings();
			for(PizzaTopping topping : toppings)
			{
				if (item.getTopping().equals(topping))
				{
					item.setCount(item.getCount() - 1);
					stockDataService.saveAmount(item);
				}
			}
		}
		return true;
	}
	
}
