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
	private OrderDataService orderRepository;
	private StockDataService stockRepository;
	private VendorDataService vendorRepository;

	public PizzaServiceImpl(OrderDataService orderRepository, StockDataService stockRepository, VendorDataService vendorRepository)
	{
		this.orderRepository = orderRepository;
		this.stockRepository = stockRepository;
		this.vendorRepository = vendorRepository;
	}

	@Override
	public Vendor getVendorById(int id) {
		return vendorRepository.getById(id);
	}

	@Override
	public Order placeOrder(Order order) {
		List<BaseStock> baseStockList = order.getVendor().getBaseStockList();
		List<ToppingStock> toppingStockList = order.getVendor().getToppingStockList();
		
		
		Order ret = orderRepository.add(order);
		
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
		
		stockRepository.saveAmount(b);
		stockRepository.saveAmount(t);
		return ret;
	}
	
}
