package services;

import models.Order;
import models.Vendor;

public interface PizzaService {
	Vendor getVendorById(int id);
	Order placeOrder(Order order);
}
