package dataServices;

import java.util.List;

import models.BaseStock;
import models.ToppingStock;

public interface StockDataService {
	void saveAmount(BaseStock t);
	void saveAmount(ToppingStock t);
	List<BaseStock> getStockBases(int vendorId, int countryId);
	List<ToppingStock> getStockToppings(int vendorId, int countryId);
}
