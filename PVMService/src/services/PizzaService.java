package services;

import models.*;

public interface PizzaService {
	Vendor getVendorById(int id);
	boolean placeOrder(Order order);
	boolean authenticateUser(String username, String password);
	boolean setBasePrice(PizzaBase base, float value);
	boolean setToppingPrice(PizzaTopping topping, float value);
	boolean refillStockBase(BaseStock baseStock);
	boolean refillStockTopping(ToppingStock toppingStock);
}
