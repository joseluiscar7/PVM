package rmit.utils.workflow;


public class EngineFromType {
	private Engine engine;
	private State srcState;
	
	Engine getEngine()
	{
		return engine;
	}
	
	public EngineFromType(Engine engine, State state) {
		this.engine = engine;
		this.srcState = state;
	}

	public EngineToType to(State state)
	{
		state.registerEngine(engine);
		StateCondition result = engine.add(srcState, state, null);
		return new EngineToType(this, result);
	}
	
	public void start(StateContext data)
	{
		engine.start(srcState, data);
	}
}
