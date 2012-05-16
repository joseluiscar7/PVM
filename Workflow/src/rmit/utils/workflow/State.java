package rmit.utils.workflow;

public abstract class State {
	private Engine engine;
	
	protected StateContext getStateContext()
	{
		return engine.getStateContext();
	}
	
	public void stop()
	{
		this.engine.exit(this);
	}

	void registerEngine(Engine engine) {
		this.engine = engine;
	}
	
	public abstract void start();
}
