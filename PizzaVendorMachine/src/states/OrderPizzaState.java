package states;

import java.io.IOException;

import presenters.*;

import rmit.mvvm.*;
import rmit.utils.workflow.State;
import viewmodels.*;
import views.*;

public class OrderPizzaState extends State {

	@Override
	public void start() {
		Presenter presenter = new PlaceOrderPresenter(this);
		View view= new PlaceOrderView(); 
		MVVMFramework framework = new MVVMFramework(presenter, view, PlaceOrderViewModel.class);
		framework.load();
	}
	public PVMStateContext getStateContext()
	{
		return (PVMStateContext)super.getStateContext();
	}
}
