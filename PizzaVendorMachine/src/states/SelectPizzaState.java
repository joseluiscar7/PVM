package states;
import java.io.IOException;

import presenters.SelectPizzaPresenter;

import rmit.mvvm.MVVMFramework;
import rmit.mvvm.Presenter;
import rmit.mvvm.View;
import rmit.utils.workflow.State;
import services.PizzaService;
import thinlet.*;
import viewmodels.SelectPizzaViewModel;
import views.SelectPizzaView;

public class SelectPizzaState extends State {
	private PizzaService pizzaService; 
	
	public SelectPizzaState(PizzaService pizzaService) {
		this.pizzaService = pizzaService;
	}

	@Override
	public void start() {
		Presenter presenter = new SelectPizzaPresenter(this, pizzaService);
		View view= new SelectPizzaView(); 
		MVVMFramework framework = new MVVMFramework(presenter, view, SelectPizzaViewModel.class);
		framework.load();
	}
	
	public void stop()
	{
		this.exit();
	}
}
