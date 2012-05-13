package rmit.mvvm;

public abstract class View {
	ViewModel viewModel;
	
	public ViewModel getViewModel()
	{
		return viewModel;
	}
	
	void setViewModel(ViewModel viewModel)
	{
		this.viewModel = viewModel;
	}
	
	public abstract void load();
}
