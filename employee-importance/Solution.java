import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

// Employee info
class Employee {
	// It's the unique id of each node;
	// unique id of this employee
	public int id;
	// the importance value of this employee
	public int importance;
	// the id of direct subordinates
	public List<Integer> subordinates;
};

public class Solution {
	public int getImportance(List<Employee> employees, int id) {
		Map<Integer, Employee> id2employee = employees.stream()
				.collect(Collectors.toMap(employee -> employee.id, Function.identity()));

		return search(id2employee, id);
	}

	int search(Map<Integer, Employee> id2employee, int id) {
		return id2employee.get(id).importance + id2employee.get(id).subordinates.stream()
				.mapToInt(subordinate -> search(id2employee, subordinate)).sum();
	}
}
