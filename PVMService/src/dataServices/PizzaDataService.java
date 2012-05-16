package dataServices;

import models.*;

public interface PizzaDataService {
	PizzaBase getPizzaBaseById(int baseId, int countryId);
	PizzaTopping getPizzaToppingById(int toppingId, int countryId);
}
