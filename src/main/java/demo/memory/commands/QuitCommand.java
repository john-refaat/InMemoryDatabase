package demo.memory.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import demo.memory.exceptions.DatabaseQuitException;
import demo.memory.io.OutputManager;

/**
 * This class represents the Quit command
 * @author john
 *
 */
@Component
@Scope("prototype")
public class QuitCommand implements Command {

	@Autowired
	private OutputManager outputManager;
	
	@Override
	public void execute(String[] paramString) {
		outputManager.writeMessage("command.quit.success");
		throw new DatabaseQuitException();
	}

	
	
}
