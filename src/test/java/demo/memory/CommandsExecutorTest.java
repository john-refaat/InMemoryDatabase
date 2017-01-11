package demo.memory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import demo.memory.commands.CommandsExecutor;
import demo.memory.conf.InMemDBConf;
import demo.memory.parser.CommandsParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = InMemDBConf.class, loader = AnnotationConfigContextLoader.class)
public class CommandsExecutorTest {

	@Autowired
	private CommandsExecutor commandsExecutor;
	
	@Test
	public void addTest() {
		String command = "add 1001-Ali-Java Programmer-10000";
		String[] commandArr = new CommandsParser().parse(command);
		commandsExecutor.execute(commandArr);
	}
	
	@Test
	public void updateTest() {
		String command = "add 1002-Ahmed-IT Manager-22000";
		String[] commandArr = new CommandsParser().parse(command);
		commandsExecutor.execute(commandArr);
		String commandupdt = "update 1002-SALARY-25000";
		String[] commandArr2 = new CommandsParser().parse(commandupdt);
		commandsExecutor.execute(commandArr2);
	}
	
	@Test
	public void delTest() {

		String commanddel = "del 1002";
		String[] commanddelArr = new CommandsParser().parse(commanddel);
		commandsExecutor.execute(commanddelArr);
		
		String command = "add 1002-Ahmed-IT Manager-22000";
		String[] commandArr = new CommandsParser().parse(command);
		commandsExecutor.execute(commandArr);
		
		commandsExecutor.execute(commanddelArr);

	}
	
	@Test
	public void printTest() {

		
		String command = "add 1002-Ahmed-IT Manager-22000";
		String[] commandArr = new CommandsParser().parse(command);
		commandsExecutor.execute(commandArr);
		
		String commandprint = "print 1002";
		String[] commandprintArr = new CommandsParser().parse(commandprint);
		commandsExecutor.execute(commandprintArr);

	}
	
	@Test
	public void printAllASCTest() {

		
		String command = "add 1002-Ahmed-IT Manager-22000";
		String[] commandArr = new CommandsParser().parse(command);
		commandsExecutor.execute(commandArr);
		

		String command2 = "add 1002-Ali-Java Programmer-10000";
		String[] commandArr2 = new CommandsParser().parse(command2);
		commandsExecutor.execute(commandArr2);
		
		
		String commandprint = "printall ASC";
		String[] commandprintArr = new CommandsParser().parse(commandprint);
		commandsExecutor.execute(commandprintArr);

	}
	
	@Test
	public void printAllDESCTest() {

		String commandA = "add 1002-Ali-Java Programmer-10000";
		String[] commandArrA = new CommandsParser().parse(commandA);
		commandsExecutor.execute(commandArrA);
		
		String command = "add 1003-Fouad-HR OFFICER-25000";
		String[] commandArr = new CommandsParser().parse(command);
		commandsExecutor.execute(commandArr);

		
		
		String commandprint = "printall DESC";
		String[] commandprintArr = new CommandsParser().parse(commandprint);
		commandsExecutor.execute(commandprintArr);

	}
}
