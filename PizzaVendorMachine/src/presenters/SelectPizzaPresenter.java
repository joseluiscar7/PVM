package presenters;

import java.util.*;

import models.*;

import rmit.mvvm.*;
import services.PizzaService;
import states.SelectPizzaState;
import viewmodels.SelectPizzaViewModel;

public class SelectPizzaPresenter extends Presenter {
	private SelectPizzaState selectPizzaState;
	private PizzaService pizzaService;
	private Vendor vendor;
	
	public SelectPizzaPresenter(SelectPizzaState selectPizzaState, PizzaService pizzaService) {
		this.selectPizzaState = selectPizzaState;
		this.pizzaService = pizzaService;
	}

	public SelectPizzaViewModel getViewModel()
	{
		return (SelectPizzaViewModel)super.getViewModel();
	}
	
	public void load()
	{
		vendor = pizzaService.getVendorById(1);
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
	}
	
	@BindEvent(name="SelectPizzaBase")
	public void onSelectPizzaBase(Object[] args)
	{
		selectPizzaState.getStateContext().setPizzaBase(vendor.getBaseStockList().get((Integer)args[0]).getBase());
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
		selectPizzaState.getStateContext().setPizzaToppings((PizzaTopping[]) selectedToppings.toArray(new PizzaTopping[] {}));
	}
	
	@BindEvent(name="Confirm")
	public void onConfirm(Object[] args)
	{
		getViewModel().setExitView(true);
		System.out.println(selectPizzaState.getStateContext().getPizzaToppings()[0].getName());
		selectPizzaState.stop();
	}
}
