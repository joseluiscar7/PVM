package presenters;

import java.util.*;

import models.*;

import rmit.mvvm.*;
import services.PizzaService;
import states.PVMStateContext;
import states.SelectPizzaState;
import viewmodels.SelectPizzaViewModel;

public class SelectPizzaPresenter extends Presenter {
	private SelectPizzaState state;
	private PizzaService pizzaService;
	private Vendor vendor;
	
	public SelectPizzaPresenter(SelectPizzaState selectPizzaState, PizzaService pizzaService) {
		this.state = selectPizzaState;
		this.pizzaService = pizzaService;
	}

	public SelectPizzaViewModel getViewModel()
	{
		return (SelectPizzaViewModel)super.getViewModel();
	}
	
	public void load()
	{
		vendor = pizzaService.getVendorById(state.getStateContext().getVendorID());
		List<String> pizzaBaseList = new ArrayList();
		List<BaseStock> baseList = vendor.getBaseStockList();
		for(BaseStock bs : baseList)
		{
			pizzaBaseList.add(bs.getBase().getName());
		}
		
		List<String> pizzaToppingList = new ArrayList();
		List<ToppingStock> toppingList = vendor.getToppingStockList();
		for(ToppingStock ts : toppingList)
		{
			pizzaToppingList.add(ts.getTopping().getName());
		}

		getViewModel().setPizzaBaseList(pizzaBaseList);
		getViewModel().setPizzaToppingList(pizzaToppingList);
		
		PVMStateContext context = state.getStateContext();
		context.setPizzaBase(null);
		context.setPizzaToppings(null);
		updatePrice();		
	}
	
	private void updatePrice()
	{
		PVMStateContext context = state.getStateContext();
		float price = 0, tax = 0;
		PizzaBase pizzaBase = context.getPizzaBase();
		if (pizzaBase != null)
		{
			price = pizzaBase.getPrice();
			tax = pizzaBase.getTax();
		}
		PizzaTopping[] toppings = context.getPizzaToppings();
		if (toppings != null)
		{
			for(PizzaTopping t : toppings)
			{
				price += t.getPrice();
				tax += t.getTax();			
			}
		}
		context.setPrice(price);
		context.setTax(tax);
		getViewModel().setPrice(price);
	}
	
	@BindEvent(name="SelectPizzaBase")
	public void onSelectPizzaBase(Object[] args)
	{
		state.getStateContext().setPizzaBase(vendor.getBaseStockList().get((Integer)args[0]).getBase());
		updatePrice();
	}
	
	@BindEvent(name="SelectPizzaToppings")
	public void onSelectPizzaToppings(Object[] args)
	{
		Object[] indices = (Object[])args[0];
		List selectedToppings = new ArrayList();
		for(Object i:indices)
		{
			selectedToppings.add(vendor.getToppingStockList().get((Integer)i).getTopping());			
		}
		state.getStateContext().setPizzaToppings((PizzaTopping[]) selectedToppings.toArray(new PizzaTopping[] {}));
		updatePrice();
	}
	
	@BindEvent(name="Confirm")
	public void onConfirm(Object[] args)
	{
		PVMStateContext context = state.getStateContext();
		if (context.getPizzaBase() == null ||  context.getPizzaToppings() == null)
			return;
		getViewModel().setExitView(true);
		state.stop();
	}
}
