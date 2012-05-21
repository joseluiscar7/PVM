package presenters;

import java.util.*;

import models.*;
import rmit.mvvm.*;
import states.*;
import viewmodels.*;

public class InitPresenter extends Presenter {
	private InitState state;
	
	public InitPresenter(InitState state) {
		this.state = state;
	}

	public InitViewModel getViewModel()
	{
		return (InitViewModel)super.getViewModel();
	}

	@BindEvent(name="Select")
	public void onSelect(Object[] args)
	{
		getViewModel().setExitView(true);
		state.getStateContext().setVendor(state.getStateContext().getPizzaService().getVendorById((Integer)args[0]));
		state.stop();
	}
	
	@BindEvent(name="Service")
	public void onService(Object[] args)
	{
		getViewModel().setExitView(true);
		state.getStateContext().setVendor(state.getStateContext().getPizzaService().getVendorById((Integer)args[0]));
		state.getStateContext().setShowAdmin(true);
		state.stop();
	}
}
