package rmit.mvvm;

import java.util.*;

class ValueProxy {
	private Map<String, ValueObservable> valueMap = new HashMap();
	private Map<String, EventObservable> eventMap = new HashMap();
	private Map<String, Object> objectMap = new HashMap();
	
	public void addValueObservable(String name, ValueObserver observer)
	{
		ValueObservable valueObservable;
		if (!valueMap.containsKey(name))
		{
			valueObservable = new ValueObservable();
			valueMap.put(name, valueObservable);
		}
		else
			valueObservable = valueMap.get(name);
		
		valueObservable.addObserver(observer);
	}
	
	public void setValue(String name, Object value)
	{
		if (!valueMap.containsKey(name))
		{
			objectMap.put(name, value);
			return;
		}
		
		valueMap.get(name).setValue(value);
	}
	
	public Object getValue(String name)
	{
		if (!valueMap.containsKey(name))
			return objectMap.containsKey(name) ? objectMap.get(name) : null;
		
		return valueMap.get(name).getValue();
	}

	public void addEventObservable(String name, EventObserver observer)
	{
		EventObservable eventObservable;
		if (!eventMap.containsKey(name))
		{
			eventObservable = new EventObservable();
			eventMap.put(name, eventObservable);
		}
		else
			eventObservable = eventMap.get(name);
		
		eventObservable.addObserver(observer);
	}
	
	public void invokeEvent(String name, Object[] args) {
		if (!eventMap.containsKey(name))
			return;
		
		EventObservable eventObservable = eventMap.get(name);
		eventObservable.invoke(args);
	}
}
