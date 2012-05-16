package services;


public class ServiceLocator
{
	private static PizzaService pizzaService;
	public static PizzaService getPizzaService()
	{
		if (pizzaService == null)
			pizzaService = new PizzaServiceImpl(null, null, null);
		return pizzaService;
	}
}