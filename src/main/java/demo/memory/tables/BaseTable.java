package demo.memory.tables;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import demo.memory.enums.OrderEnum;
import demo.memory.model.BaseModel;

/**
 * An Abstract Generic class that can be extended by any class that represents a concrete in memory table
 * 
 * @author john
 *
 * @param <T>
 */
public abstract class BaseTable<T extends BaseModel> {

	/**
	 * ConcurrentHashMap to save the data and ensure thread-safety. In case multiple threads are executed.
	 */
	private final Map<Integer, T> records = new ConcurrentHashMap<>();

	public void add(Integer id, T t) {
		if (!records.containsKey(id)) {
			records.put(id, t);
		}
	}

	public T get(int id) {
		return records.get(id);
	}

	public void update(Integer id, T t) {
		if (records.containsKey(id)) {
			records.put(id, t);
		}
	}

	public void delete(int id) {
		records.remove(id);
	}

	protected List<T> getrecords(OrderEnum order, Comparator<T> comparator) {
		return records.values().stream().sorted((e1, e2) -> order.getFactor() * comparator.compare(e1, e2))
				.collect(Collectors.toList());
	}
	
	public Integer getCount() {
		return records.size();
	}
	
	protected abstract Comparator<T> getComparator();
	
	public abstract List<T> getAll(OrderEnum order);
}
