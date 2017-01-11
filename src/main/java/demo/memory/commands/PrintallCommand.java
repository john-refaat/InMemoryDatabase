package demo.memory.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import demo.memory.enums.OrderEnum;
import demo.memory.io.OutputManager;
import demo.memory.model.Employee;
import demo.memory.tables.EmployeeTable;

/**
 * This class represents the Print All command
 * 
 * @author john
 *
 */

@Component
@Scope("prototype")
public class PrintallCommand implements Command {

	@Autowired
	private EmployeeTable employeeTable;

	@Autowired
	private OutputManager outputManager;

	/*public PrintallCommand(EmployeeTable employeeTable) {
		this.employeeTable = employeeTable;
	}*/

	@Override
	public void execute(String[] paramString) {
		synchronized (employeeTable) {
			List<Employee> employees = employeeTable.getAll(OrderEnum.valueOf(paramString[0]));
			for (Employee emp : employees) {
				outputManager.writeMessage("command.printall.success",
						new Object[] { emp.getName(), emp.getDesignation()});
			}
		}
	}

}
