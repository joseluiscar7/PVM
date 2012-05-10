package repositories;

import models.BaseStock;
import models.ToppingStock;

public interface StockRepository {
	void save(BaseStock t);
	void save(ToppingStock t);
}
