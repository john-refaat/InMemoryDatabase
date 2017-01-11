package demo.memory.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import demo.memory.enums.CommandEnum;

/**
 * Factory for creating a command object.
 * 
 * @author john
 *
 */
@Component
public class CommandsFactory {

	@Autowired
    private ApplicationContext context;
	
	public Command createCommand(CommandEnum commandEnum) {
		Command command = null;
		switch (commandEnum) {
		case ADD:
			command = context.getBean(AddCommand.class);//new AddCommand(employeeTable);
			break;
		case UPDATE:
			command = context.getBean(UpdateCommand.class);//new UpdateCommand(employeeTable);
			break;
		case DELETE:
			command = context.getBean(DeleteCommand.class);//new DeleteCommand(employeeTable);
			break;
		case PRINT:
			command = context.getBean(PrintCommand.class);//new PrintCommand(employeeTable);
			break;
		case PRINTALL:
			command = context.getBean(PrintallCommand.class);//new PrintallCommand(employeeTable);
			break;
		case QUIT:
			command = context.getBean(QuitCommand.class);//new QuitCommand();
			break;
		default:
			break;
		}
		return command;
	}
}
