package services;

import java.util.List;

import dataServices.*;
import models.*;

public class PizzaServiceImpl implements PizzaService {
	private OrderDataService orderDataService;
	private StockDataService stockDataService;
	private VendorDataService vendorDataService;
	private PizzaDataService pizzaDataService;

	public PizzaServiceImpl(OrderDataService orderDataService, StockDataService stockDataService, VendorDataService vendorDataService, PizzaDataService pizzaDataService)
	{
		this.orderDataService = orderDataService;
		this.stockDataService = stockDataService;
		this.vendorDataService = vendorDataService;
		this.pizzaDataService = pizzaDataService;
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

	@Override
	public boolean authenticateUser(String username, String password) {
		if (username.equals("admin") && password.equals("admin"))
			return true;
		return false;
	}

	@Override
	public boolean setBasePrice(PizzaBase base, float value) {
		if (pizzaDataService.updatePizzaBasePrice(base.getCountry().getId(), base.getId(), value))
		{
			base.setPrice(value);
			return true;
		}
		return false;
	}

	@Override
	public boolean setToppingPrice(PizzaTopping topping, float value) {
		if (pizzaDataService.updatePizzaToppingPrice(topping.getCountry().getId(), topping.getId(), value))
		{
			topping.setPrice(value);
			return true;
		}
		return false;
	}

	@Override
	public boolean refillStockBase(BaseStock baseStock) {
		int amount = 10;
		if (baseStock.getCount() == amount)
			return true;
		
		if (stockDataService.updateStockBaseCount(baseStock.getVendorId(), baseStock.getBase().getId(), amount))
		{
			baseStock.setCount(amount);
			return true;
		}
		return false;
	}

	@Override
	public boolean refillStockTopping(ToppingStock toppingStock) {
		int amount = 40;
		if (toppingStock.getCount() == amount)
			return true;
		
		if (stockDataService.updateStockToppingCount(toppingStock.getVendorId(), toppingStock.getTopping().getId(), amount))
		{
			toppingStock.setCount(amount);
			return true;
		}
		return false;
	}
	
	
}
