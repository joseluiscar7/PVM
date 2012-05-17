package states;

import rmit.mvvm.*;
import rmit.utils.workflow.State;
import presenters.*;
import viewmodels.*;
import views.*;

public class InitState extends State {

	@Override
	public void start() {
		Presenter presenter = new InitPresenter(this);
		View view= new InitView(); 
		MVVMFramework framework = new MVVMFramework(presenter, view, InitViewModel.class);
		framework.load();
	}
	
	public PVMStateContext getStateContext()
	{
		return (PVMStateContext)super.getStateContext();
	}
}
