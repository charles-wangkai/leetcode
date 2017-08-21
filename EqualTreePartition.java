import java.util.HashMap;
import java.util.Map;

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class EqualTreePartition {
	public boolean checkEqualTree(TreeNode root) {
		Map<Integer, Integer> sum2count = new HashMap<Integer, Integer>();

		int rootSum = search(root, sum2count);
		sum2count.put(rootSum, sum2count.get(rootSum) - 1);
		return rootSum % 2 == 0 && sum2count.getOrDefault(rootSum / 2, 0) > 0;
	}

	int search(TreeNode node, Map<Integer, Integer> sum2count) {
		if (node == null) {
			return 0;
		}

		int sum = node.val + search(node.left, sum2count) + search(node.right, sum2count);
		sum2count.put(sum, sum2count.getOrDefault(sum, 0) + 1);
		return sum;
	}
}
