package demo.memory.tables;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import demo.memory.enums.OrderEnum;
import demo.memory.model.Employee;

/**
 * This class represents an in memory table for Employees.
 * 
 * @author john
 *
 */
@Component
public class EmployeeTable extends BaseTable<Employee> {

	@Override
	protected Comparator<Employee> getComparator() {
		return (e1,e2)->e1.getName().compareTo(e2.getName());
	}

	@Override
	public List<Employee> getAll(OrderEnum order) {
		return getrecords(order, getComparator());
	}
	
	
	
	
}
