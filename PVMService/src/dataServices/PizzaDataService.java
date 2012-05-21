package dataServices;

import models.*;

public interface PizzaDataService {
	PizzaBase getPizzaBaseById(int baseId, Country country);
	PizzaTopping getPizzaToppingById(int toppingId, Country country);
	boolean updatePizzaBasePrice(int countryId, int baseId, float value);
	boolean updatePizzaToppingPrice(int countryId, int toppingId, float value);	
}
