package presenters;

import java.util.*;

import models.*;

import rmit.mvvm.*;
import services.*;
import states.*;
import viewmodels.*;

public class SelectPizzaPresenter extends Presenter {
	private SelectPizzaState state;
	private PizzaService pizzaService;
	private Vendor vendor;
	
	public SelectPizzaPresenter(SelectPizzaState selectPizzaState) {
		this.state = selectPizzaState;
		this.pizzaService = state.getStateContext().getPizzaService();
	}

	public SelectPizzaViewModel getViewModel()
	{
		return (SelectPizzaViewModel)super.getViewModel();
	}
	
	public void load()
	{
		vendor = state.getStateContext().getVendor();
		List<PizzaBaseListViewModel> pizzaBaseList = new ArrayList();
		List<BaseStock> baseList = vendor.getBaseStockList();
		for(BaseStock bs : baseList)
		{
			PizzaBase b = bs.getBase();
			pizzaBaseList.add(new PizzaBaseListViewModel(b.getName(), b.getPrice(), bs.getCount()));
		}
		
		List<PizzaToppingListViewModel> pizzaToppingList = new ArrayList();
		List<ToppingStock> toppingList = vendor.getToppingStockList();
		for(ToppingStock ts : toppingList)
		{
			PizzaTopping t = ts.getTopping();
			pizzaToppingList.add(new PizzaToppingListViewModel(t.getName(), t.getPrice(), ts.getCount()));
		}

		getViewModel().setPizzaBaseList(pizzaBaseList);
		getViewModel().setPizzaToppingList(pizzaToppingList);
		getViewModel().setCurrency(vendor.getCountry().getCurrencyName());
		
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
		int idx = (Integer)args[0];
		if (idx >= 0)
		{
			state.getStateContext().setPizzaBase(vendor.getBaseStockList().get(idx).getBase());
			updatePrice();
		}
		else
		{
			state.getStateContext().setPizzaBase(null);
			getViewModel().setPrice(0);
		}
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
