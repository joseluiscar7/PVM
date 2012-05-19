package services;

import dataAccess.*;
import dataServices.*;


public class ServiceLocator
{
	private static PizzaService pizzaService;
	public static PizzaService getPizzaService()
	{
		if (pizzaService == null)
		{
			SessionManager sm = SessionManagerFactory.createSessionManager();
			CountryDataService countryDataService = new CountryDataServiceImpl(sm);
			PizzaInfoDataService pizzaInfoDataService = new PizzaInfoDataServiceImpl(sm);
			PizzaDataService pizzaDataService = new PizzaDataServiceImpl(sm, pizzaInfoDataService);
			StockDataService stockDataService = new StockDataServiceImpl(sm, pizzaDataService);
			VendorDataService vendorDataService = new VendorDataServiceImpl(sm, countryDataService, stockDataService);
			OrderDataService orderDataService = new OrderDataServiceImpl(sm);
			pizzaService = new PizzaServiceImpl(orderDataService, stockDataService, vendorDataService);
		}
		return pizzaService;
	}
}