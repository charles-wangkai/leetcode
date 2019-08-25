import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

public class DinnerPlates {
	private int capacity;
	private List<Stack<Integer>> stacks = new ArrayList<>();
	private SortedSet<Integer> nonFullIndices = new TreeSet<>();
	private SortedSet<Integer> nonEmptyIndices = new TreeSet<>();

	public DinnerPlates(int capacity) {
		this.capacity = capacity;
	}

	public void push(int val) {
		if (nonFullIndices.isEmpty()) {
			stacks.add(new Stack<>());
			nonFullIndices.add(stacks.size() - 1);
		}

		int index = nonFullIndices.first();
		stacks.get(index).push(val);

		if (stacks.get(index).size() == capacity) {
			nonFullIndices.remove(index);
		}

		nonEmptyIndices.add(index);
	}

	public int pop() {
		if (nonEmptyIndices.isEmpty()) {
			return -1;
		}

		int index = nonEmptyIndices.last();
		int result = stacks.get(index).pop();

		nonFullIndices.add(index);

		if (stacks.get(index).empty()) {
			nonEmptyIndices.remove(index);
		}

		return result;
	}

	public int popAtStack(int index) {
		if (index >= stacks.size() || stacks.get(index).empty()) {
			return -1;
		}

		int result = stacks.get(index).pop();

		nonFullIndices.add(index);

		if (stacks.get(index).empty()) {
			nonEmptyIndices.remove(index);
		}

		return result;
	}
}

// Your DinnerPlates object will be instantiated and called as such:
// DinnerPlates obj = new DinnerPlates(capacity);
// obj.push(val);
// int param_2 = obj.pop();
// int param_3 = obj.popAtStack(index);
