package rmit.utils.workflow;

import java.util.*;

public class Engine {
	private StateData data;
	private Map<State, List<StateCondition>> stateMap = new HashMap();
	
	StateCondition add(State src, State dst, Condition cond)
	{
		List<StateCondition> scList = null;
		if (!stateMap.containsKey(src))
		{
			scList = new ArrayList();
			stateMap.put(src, scList);
		}
		else
			scList = stateMap.get(src);
		
		StateCondition result = new StateCondition(dst, cond);
		scList.add(result);
		return result;
	}
	
	void start(State state, StateData data)
	{
		state.registerEngine(this);
		this.data = data;
		state.start();
	}
	
	public EngineFromType from(State state)
	{
		state.registerEngine(this);
		return new EngineFromType(this, state);
	}

	StateData getStateData() {
		return data;
	}

	void exit(State state) {
		if (!stateMap.containsKey(state))
			return;
		
		List<StateCondition> scList = stateMap.get(state);
		for(StateCondition sc : scList)
		{
			if (sc.condition != null && !sc.condition.check(data))
				continue;
			sc.state.start();
			break;
		}
	}
	
}
