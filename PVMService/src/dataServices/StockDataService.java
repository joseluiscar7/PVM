package dataServices;

import java.util.List;

import models.BaseStock;
import models.Country;
import models.ToppingStock;

public interface StockDataService {
	void saveAmount(BaseStock t);
	void saveAmount(ToppingStock t);
	List<BaseStock> getStockBases(int vendorId, Country country);
	List<ToppingStock> getStockToppings(int vendorId, Country country);
}
