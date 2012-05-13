package rmit.mvvm;

public abstract class Presenter {
	private ViewModel viewModel;
	
	public ViewModel getViewModel()
	{
		return viewModel;
	}
	
	void setViewModel(ViewModel viewModel)
	{
		this.viewModel = viewModel;
	}
	
	public void load() {}
}
