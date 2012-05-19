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
	
	public void load()
	{
	}
	
	private void selectVendor(int vendorID)
	{
		getViewModel().setExitView(true);
		state.getStateContext().setVendor(state.getStateContext().getPizzaService().getVendorById(vendorID));
		state.stop();
	}
	
	@BindEvent(name="Select")
	public void onSelect(Object[] args)
	{
		selectVendor((Integer)args[0]);
	}
	
	@BindEvent(name="Service")
	public void onService(Object[] args)
	{
		getViewModel().setExitView(true);
		state.stop();
	}
}
