package states;
import presenters.SelectPizzaPresenter;

import rmit.mvvm.MVVMFramework;
import rmit.mvvm.Presenter;
import rmit.mvvm.View;
import rmit.utils.workflow.State;
import services.PizzaService;
import viewmodels.SelectPizzaViewModel;
import views.SelectPizzaView;

public class SelectPizzaState extends State {
	private PizzaService pizzaService; 

	@Override
	public void start() {
		Presenter presenter = new SelectPizzaPresenter(this);
		View view= new SelectPizzaView(); 
		MVVMFramework framework = new MVVMFramework(presenter, view, SelectPizzaViewModel.class);
		framework.load();
	}
	
	public PVMStateContext getStateContext()
	{
		return (PVMStateContext)super.getStateContext();
	}
}
