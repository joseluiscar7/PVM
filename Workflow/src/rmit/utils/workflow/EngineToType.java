package rmit.utils.workflow;

public class EngineToType {
	private EngineFromType engineFromType;
	private StateCondition sc;
	
	public EngineToType(EngineFromType engineFromType, StateCondition sc) {
		this.engineFromType = engineFromType;
		this.sc = sc;
	}

	public void when(Condition condition)
	{
		sc.condition = condition;
	}
	
	public EngineToType to(State state)
	{
		return new EngineFromType(engineFromType.getEngine(), sc.state).to(state);
	}
}
