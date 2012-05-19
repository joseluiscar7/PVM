package viewmodels;

import java.util.List;

import rmit.mvvm.ViewModel;

public interface SelectPizzaViewModel extends ViewModel {
	List<PizzaBaseListViewModel> getPizzaBaseList();
	void setPizzaBaseList(List<PizzaBaseListViewModel> value);
	
	List<PizzaToppingListViewModel> getPizzaToppingList();
	void setPizzaToppingList(List<PizzaToppingListViewModel> value);
	
	float getPrice();
	void setPrice(float value);
	
	boolean getExitView();
	void setExitView(boolean value);
	
	String getCurrency();
	void setCurrency(String value);
	
	void eventSelectPizzaBase(int index);
	void eventSelectPizzaToppings(Object[] indices);
	void eventConfirm();
}
