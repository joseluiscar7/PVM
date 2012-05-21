package states;

import rmit.mvvm.*;
import rmit.utils.workflow.State;
import presenters.*;
import viewmodels.*;
import views.*;

public class AdminState extends State {

	@Override
	public void start() {
		Presenter presenter = new AdminPresenter(this);
		View view= new AdminView(); 
		MVVMFramework framework = new MVVMFramework(presenter, view, AdminViewModel.class);
		framework.load();
	}
	
	public PVMStateContext getStateContext()
	{
		return (PVMStateContext)super.getStateContext();
	}
}
