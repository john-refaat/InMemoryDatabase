package demo.memory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import demo.memory.commands.CommandsExecutor;
import demo.memory.conf.InMemDBConf;
import demo.memory.exceptions.DatabaseQuitException;
import demo.memory.exceptions.DatabaseRuntimeException;
import demo.memory.io.InputManager;
import demo.memory.io.OutputManager;
import demo.memory.parser.CommandsParser;

/**
 * In Memory Database Main class. This class takes command input from the
 * console until the user enters the quit command
 * 
 * @author john
 *
 */
@Component
public class DataBase {

	@Autowired
	private CommandsExecutor commandsExecutor;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(InMemDBConf.class);
		ctx.getBean(DataBase.class).run();
	}

	public void run() {
		String command = "";
		InputManager inputManager = new InputManager(new BufferedReader(new InputStreamReader(System.in)));
		while (true) {
			try {
				command = inputManager.read();
				if (StringUtils.isNotBlank(command)) {
					String[] commandArr = new CommandsParser().parse(command);
					if (commandArr.length > 0) {
						// new CommandsExecutor().execute(commandArr);
						commandsExecutor.execute(commandArr);
					}
				}
			} catch (DatabaseQuitException e) {
				break;
			} catch (DatabaseRuntimeException e) {
				new OutputManager().write(e.getMessage());
			}
		}
	}

}
