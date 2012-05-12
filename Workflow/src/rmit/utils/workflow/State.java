package rmit.utils.workflow;

public abstract class State {
	private Engine engine;
	
	protected StateData getStateData()
	{
		return engine.getStateData();
	}
	
	protected void exit()
	{
		this.engine.exit(this);
	}

	void registerEngine(Engine engine) {
		this.engine = engine;
	}
	
	public abstract void start();
}
