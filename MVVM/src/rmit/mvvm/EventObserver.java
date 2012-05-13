package rmit.mvvm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Observable;
import java.util.Observer;

class EventObserver implements Observer {
	private Object invoker;
	private Method method;
	
	public EventObserver(Object invoker, Method method)
	{
		this.invoker = invoker;
		this.method = method;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			method.invoke(invoker, arg1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
