package dataServices;

import models.*;

public interface PizzaDataService {
	PizzaBase getPizzaBaseById(int baseId, Country country);
	PizzaTopping getPizzaToppingById(int toppingId, Country country);
}
