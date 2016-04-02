import java.util.List;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {
	// @return true if this NestedInteger holds a single integer, rather than a
	// nested list.
	public boolean isInteger();

	// @return the single integer that this NestedInteger holds, if it holds a
	// single integer
	// Return null if this NestedInteger holds a nested list
	public Integer getInteger();

	// @return the nested list that this NestedInteger holds, if it holds a
	// nested list
	// Return null if this NestedInteger holds a single integer
	public List<NestedInteger> getList();
}

public class NestedListWeightSum {
	int sum;

	public int depthSum(List<NestedInteger> nestedList) {
		sum = 0;
		for (NestedInteger ni : nestedList) {
			search(ni, 1);
		}
		return sum;
	}

	void search(NestedInteger ni, int depth) {
		if (ni.isInteger()) {
			sum += depth * ni.getInteger();
		} else {
			for (NestedInteger subNi : ni.getList()) {
				search(subNi, depth + 1);
			}
		}
	}
}
