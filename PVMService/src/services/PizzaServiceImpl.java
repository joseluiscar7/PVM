package services;

import java.util.List;

import repositories.OrderRepository;
import repositories.StockRepository;
import repositories.VendorRepository;
import models.BaseStock;
import models.Order;
import models.ToppingStock;
import models.Vendor;

public class PizzaServiceImpl implements PizzaService {
	private OrderRepository orderRepository;
	private StockRepository stockRepository;
	private VendorRepository vendorRepository;
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
		
		stockRepository.save(b);
		stockRepository.save(t);
		return ret;
	}
	
}
