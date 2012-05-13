package rmit.mvvm;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class MVVMFramework {
	private Presenter presenter;
	private View view;
	private Class viewModelClass;
	private ValueProxy valueProxy;
	
	public MVVMFramework(Presenter presenter, View view, Class viewModelClass)
	{
		this.presenter = presenter;
		this.view = view;
		this.viewModelClass = viewModelClass;
		valueProxy = new ValueProxy();
	}
	
	public void load()
	{
		generateValueProxy(presenter);
		generateValueProxy(view);
		
		ViewModel viewModel = (ViewModel) Proxy.newProxyInstance(viewModelClass.getClassLoader()
				, new Class[] { viewModelClass }
				, new ViewModelProxyInvocationHandler(valueProxy));
		
		presenter.setViewModel(viewModel);
		view.setViewModel(viewModel);
		
		presenter.load();
		view.load();
	}
	
	private void generateValueProxy(Object object)
	{
		Method[] presenterMethods = object.getClass().getMethods();
		for(Method m : presenterMethods)
		{
			Annotation[] annotations = m.getDeclaredAnnotations();
			for(Annotation annt : annotations)
			{
				if (annt instanceof BindEvent)
				{
					valueProxy.addEventObservable(((BindEvent)annt).name(), new EventObserver(object, m));
				}
				else if (annt instanceof BindValue)
				{
					valueProxy.addValueObservable(((BindValue)annt).name(), new ValueObserver(object, m));
				}
				
			}
		}
	}
}
