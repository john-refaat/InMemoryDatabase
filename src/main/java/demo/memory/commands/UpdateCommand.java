package demo.memory.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import demo.memory.enums.PropertyEnum;
import demo.memory.io.OutputManager;
import demo.memory.model.Employee;
import demo.memory.tables.EmployeeTable;

/**
 * This class represents the Update command
 * 
 * @author john
 *
 */

@Component
@Scope("prototype")
public class UpdateCommand implements Command {

	@Autowired
	private EmployeeTable employeeTable;

	@Autowired
	private OutputManager outputManager;

/*	public UpdateCommand(EmployeeTable employeeTable) {
		this.employeeTable = employeeTable;
	}*/

	@Override
	public void execute(String[] paramString) {
		synchronized (employeeTable) {

			Employee emp = employeeTable.get(Integer.parseInt(paramString[0]));
			if (emp == null) {
				outputManager.writeMessage("command.update.fail.notfound", new Object[] { paramString[0] });
				return;
			}

			PropertyEnum property = PropertyEnum.valueOf(paramString[1]);
			switch (property) {
			case NAME:
				emp.setName(paramString[2]);
				break;
			case DESIG:
				emp.setDesignation(paramString[2]);
				break;
			case SALARY:
				emp.setMonthlySalary(Integer.parseInt(paramString[2]));
				break;
			default:
				break;
			}
			employeeTable.update(emp.getId(), emp);
			outputManager.writeMessage("command.update.success",
					new Object[] { String.valueOf(emp.getId()), emp.getName(), emp.getDesignation(), emp.getMonthlySalary() });

		}
	}

}
