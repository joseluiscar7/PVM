package presenters;

import java.util.*;
import models.*;
import rmit.mvvm.*;
import states.*;
import viewmodels.*;

public class AdminPresenter extends Presenter {
	private AdminState state;
	private List<BaseStock> baseStocks;
	private List<ToppingStock> toppingStocks;
	
	public AdminPresenter(AdminState state) {
		this.state = state;
	}

	public AdminViewModel getViewModel()
	{
		return (AdminViewModel)super.getViewModel();
	}
	
	public void load()
	{
	}
		
	@BindEvent(name="Authenticate")
	public void onAuthenticate(Object[] args)
	{
		boolean authenticationResult = state.getStateContext().getPizzaService().authenticateUser((String)args[0], (String)args[1]);
		getViewModel().setAuthenticateSuccess(authenticationResult);
		if (authenticationResult)
		{
			showStockList();
		}
	}
	
	private void showStockList()
	{
		baseStocks = state.getStateContext().getVendor().getBaseStockList();
		List<StockInfoViewModel> viewModel = new ArrayList();
		for(BaseStock bs : baseStocks)
		{
			viewModel.add(new StockInfoViewModel(bs.getBase().getName(), bs.getCount(), bs.getBase().getPrice()));
		}
		getViewModel().setBaseStockInfo(viewModel.toArray(new StockInfoViewModel[0]));
		
		toppingStocks = state.getStateContext().getVendor().getToppingStockList();
		viewModel = new ArrayList();
		for(ToppingStock ts : toppingStocks)
		{
			viewModel.add(new StockInfoViewModel(ts.getTopping().getName(), ts.getCount(), ts.getTopping().getPrice()));
		}
		getViewModel().setToppingStockInfo(viewModel.toArray(new StockInfoViewModel[0]));
	}
	
	@BindEvent(name="SetBasePrice")
	public void setBasePrice(Object[] args)
	{
		List<Integer> indices = (List<Integer>)args[0];
		float value = (Float)args[1];
		if (indices == null) 
			return;
		if (indices.size() == 1)
		{
			state.getStateContext().getPizzaService().setBasePrice(baseStocks.get(indices.get(0)).getBase(), value);
			showStockList();
			return;
		}
		
		for(int i : indices)
		{
			PizzaBase base = baseStocks.get(i).getBase();
			state.getStateContext().getPizzaService().setBasePrice(base, base.getPrice() * value);
		}
		showStockList();
	}
	
	@BindEvent(name="SetToppingPrice")
	public void setToppingPrice(Object[] args)
	{
		List<Integer> indices = (List<Integer>)args[0];
		if (indices == null) 
			return;
		float value = (Float)args[1];
		if (indices.size() == 1)
		{
			state.getStateContext().getPizzaService().setToppingPrice(toppingStocks.get(indices.get(0)).getTopping(), value);
			showStockList();
			return;
		}
		
		for(int i : indices)
		{
			PizzaTopping topping = toppingStocks.get(i).getTopping();
			state.getStateContext().getPizzaService().setToppingPrice(topping, topping.getPrice() * value);
		}
		showStockList();
	}
	
	@BindEvent(name="RefillBase")
	public void refillBase(Object[] args)
	{
		List<Integer> indices = (List<Integer>)args[0];
		if (indices == null) 
			return;
		for(int i : indices)
		{
			state.getStateContext().getPizzaService().refillStockBase(baseStocks.get(i));
		}
		showStockList();
	}
	
	@BindEvent(name="RefillTopping")
	public void refillTopping(Object[] args)
	{
		List<Integer> indices = (List<Integer>)args[0];
		if (indices == null) 
			return;
		for(int i : indices)
		{
			state.getStateContext().getPizzaService().refillStockTopping(toppingStocks.get(i));
		}
		showStockList();
	}
}
