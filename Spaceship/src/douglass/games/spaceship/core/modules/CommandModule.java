package douglass.games.spaceship.core.modules;

import java.util.*;
import douglass.games.spaceship.core.Module;

public class CommandModule extends InternalModule	{
	
	/**
	 * This is a list of all the modules that this CommandModule is in control of.  As long as there is a path from 
	 * the command module to the Module being controlled, then the controlled module is able to be used.
	 */
	private List<Module> controlled;

	public CommandModule(String name, int size, double standardMass, double power) {
		
		//A command module requires a number of people equal to twice the CommandModule's size;
		super(name, size, standardMass, power, 2*size, true);
		controlled = new ArrayList<Module>();
	}
	
	/**
	 * Calculates the number of InternalModules that this Command Module can support, as a function of the size of the command module
	 * @return 
	 */
	public int supportedModules()	{
		
		return 5*getSize();
	}
	
	//TODO add a method to search for a controlled Module to see if the command module is connected.  For now though, we'll ignore 
	//connectedness, and only worry about modules that are available in the controlled list.
	/**
	 * This method checks that the given module is available for commands.  In the Spaceship class, all commands to should be 
	 * checked through the Command Module first.  
	 * @return true if the Module is ready and available for commands, false otherwise.  Makes no distinction at this time between 
	 * a module that is not commanded by this module (not in the list) and a module existing but for whatever reason not being 
	 * available.  This may or may not be how this functionality should occur.  Certainly the mothod isCommanded is provided to 
	 * see if the given module exists in the list of Modules commanded by this command module.
	 */
	public boolean isAvailable(Module module)	{
		
		if(isCommanded(module))	{
			
			return isActivated();
		}
		
		return false;
	}
	
	public boolean isCommanded(Module module)	{
		
		return controlled.contains(module); //This should only return true if the reference to the module in the list is the same
		//as the reference to the module passed in to this method.  
		//We want this behavior since they should both be pointing to the very same object.  
	}
}
