package services;

import models.Order;
import models.Vendor;

public interface PizzaService {
	Vendor getVendorById(int id);
	boolean placeOrder(Order order);
}
