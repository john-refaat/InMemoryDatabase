package demo.memory.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import demo.memory.io.OutputManager;
import demo.memory.model.Employee;
import demo.memory.tables.EmployeeTable;

/**
 * This class represents the Add command
 * 
 * @author john
 *
 */

@Component
@Scope("prototype")
public class AddCommand implements Command {
	
	@Autowired
	private EmployeeTable employeeTable;

	@Autowired
	private OutputManager outputManager;

	/*public AddCommand(EmployeeTable employeeTable) {
		this.employeeTable = employeeTable;
	}*/

	@Override
	public void execute(String[] paramString) {
		synchronized (employeeTable) {
			Employee emp = new Employee();
			emp.setId(Integer.parseInt(paramString[0]));
			emp.setName(paramString[1]);
			emp.setDesignation(paramString[2]);
			emp.setMonthlySalary(Integer.parseInt(paramString[3]));
			employeeTable.add(emp.getId(), emp);
			int count = employeeTable.getCount();
			outputManager.writeMessage("command.add.success", new Object[] { String.valueOf(emp.getId()), count });
		}
	}

}
