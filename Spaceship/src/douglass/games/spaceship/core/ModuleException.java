package douglass.games.spaceship.core;

public class ModuleException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private Module cause;
	
	public ModuleException()	{
		
		super();
		cause = null;
	}
	
	public ModuleException(Module cause)	{
		
		super();
		this.cause = cause;
	}
	
	public ModuleException(Exception e)	{
		
		super(e);
	}
	
	public ModuleException(String message)	{
		
		super(message);
	}
	
	public Module getModuleCause()	{
		
		return cause;
	}
}
