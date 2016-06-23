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

public class NestedListWeightSum_II {
	public int depthSumInverse(List<NestedInteger> nestedList) {
		int maxDepth = findMaxDepth(nestedList);
		System.out.println("maxDepth: " + maxDepth);
		return findDepthSum(nestedList, maxDepth);
	}

	private int findDepthSum(List<NestedInteger> nestedList, int weight) {
		int depthSum = 0;
		for (NestedInteger ni : nestedList) {
			if (ni.isInteger()) {
				depthSum += weight * ni.getInteger();
			} else {
				depthSum += findDepthSum(ni.getList(), weight - 1);
			}
		}
		return depthSum;
	}

	private int findMaxDepth(List<NestedInteger> nestedList) {
		if (nestedList == null) {
			return 0;
		}

		int maxDepth = 0;
		for (NestedInteger ni : nestedList) {
			if (ni.isInteger()) {
				maxDepth = Math.max(maxDepth, 1);
			} else {
				maxDepth = Math.max(maxDepth, 1 + findMaxDepth(ni.getList()));
			}
		}
		return maxDepth;
	}
}
