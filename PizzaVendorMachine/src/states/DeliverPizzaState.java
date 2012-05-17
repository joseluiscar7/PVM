package states;

import java.io.IOException;

import presenters.*;

import rmit.mvvm.MVVMFramework;
import rmit.mvvm.Presenter;
import rmit.mvvm.View;
import rmit.utils.workflow.State;
import viewmodels.*;
import views.*;

public class DeliverPizzaState extends State {

	@Override
	public void start() {
		Presenter presenter = new DeliveryPresenter(this);
		View view= new DeliveryView(); 
		MVVMFramework framework = new MVVMFramework(presenter, view, DeliveryViewModel.class);
		framework.load();
	}
	
	public PVMStateContext getStateContext()
	{
		return (PVMStateContext)super.getStateContext();
	}

}
