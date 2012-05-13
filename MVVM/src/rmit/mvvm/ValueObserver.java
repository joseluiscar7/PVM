package rmit.mvvm;
import java.lang.reflect.Method;
import java.util.Observable;
import java.util.Observer;


class ValueObserver implements Observer {
	private Object invoker;
	private Method method;
	
	public ValueObserver(Object invoker, Method method)
	{
		this.invoker = invoker;
		this.method = method;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			method.invoke(invoker);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
