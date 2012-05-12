package rmit.utils.workflow;

class StateCondition {
	State state;
	Condition condition;
	
	StateCondition(State state, Condition condition)
	{
		this.state = state;
		this.condition = condition;
	}
}