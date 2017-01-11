package demo.memory.commands;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.memory.enums.CommandEnum;

/**
 * This class is responsible for creating the correct command object, passing to
 * it the needed parameters and invoking it to execute.
 * 
 * @author john
 *
 */

@Component
public class CommandsExecutor {

	@Autowired
	private CommandsFactory CommandsFactory;

	/**
	 * Executes the command
	 * 
	 * @param command an array representing the command after being parsed and filtered of symbols
	 */
	public void execute(String[] command) {
		Command commandObject = CommandsFactory.createCommand(CommandEnum.getEnum(command[0]));
		commandObject.execute(Arrays.copyOfRange(command, 1, command.length));
	}
}
