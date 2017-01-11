package demo.memory.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import demo.memory.io.OutputManager;
import demo.memory.model.Employee;
import demo.memory.tables.EmployeeTable;

/**
 * This class represents the Print command
 * 
 * @author john
 *
 */

@Component
@Scope("prototype")
public class PrintCommand implements Command {

	@Autowired
	private EmployeeTable employeeTable;

	@Autowired
	private OutputManager outputManager;

	/*
	 * public PrintCommand(EmployeeTable employeeTable) { this.employeeTable =
	 * employeeTable; }
	 */

	@Override
	public void execute(String[] paramString) {
		synchronized (employeeTable) {

			Employee emp = employeeTable.get(Integer.parseInt(paramString[0]));
			if (emp != null) {
				outputManager.writeMessage("command.print.success",
						new Object[] { emp.getName(), emp.getDesignation(), emp.getMonthlySalary() });
			}
		}
	}

}
