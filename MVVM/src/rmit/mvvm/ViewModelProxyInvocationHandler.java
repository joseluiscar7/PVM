package rmit.mvvm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class ViewModelProxyInvocationHandler implements InvocationHandler {
	private ValueProxy valueProxy;
	
	public ViewModelProxyInvocationHandler(ValueProxy valueProxy)
	{
		this.valueProxy = valueProxy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		String name = method.getName();
		if(name.startsWith("get"))
		{
			String propertyName = name.substring(3);
			return valueProxy.getValue(propertyName);
		}
		else if(name.startsWith("set"))
		{
			String propertyName = name.substring(3);
			valueProxy.setValue(propertyName, args[0]);
			return null;
		}
		else if(name.startsWith("event"))
		{
			String eventName = name.substring(5);
			valueProxy.invokeEvent(eventName, args);
			return null;
			
		}
		throw new Exception("Method not supported!");
	}
}