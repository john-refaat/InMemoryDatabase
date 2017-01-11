package demo.memory.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import demo.memory.io.OutputManager;
import demo.memory.model.Employee;
import demo.memory.tables.EmployeeTable;

/**
 * This class represents the Delete command
 * 
 * @author john
 *
 */
@Component
@Scope("prototype")
public class DeleteCommand implements Command {
	
	@Autowired
	private EmployeeTable employeeTable;

	@Autowired
	private OutputManager outputManager;

	

	@Override
	public void execute(String[] paramString) {
		synchronized (employeeTable) {
			Employee emp = employeeTable.get(Integer.parseInt(paramString[0]));
			if (emp == null) {
				outputManager.writeMessage("command.del.fail.notfound", new Object[] { paramString[0] });
				return;
			}
			employeeTable.delete(Integer.parseInt(paramString[0]));
			int count = employeeTable.getCount();
			outputManager.writeMessage("command.del.success", new Object[] { String.valueOf(emp.getId()), count });
		}
	}

}
