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

public class MostFrequentSubtreeSum {
	Map<Integer, Integer> sum2count;

	public int[] findFrequentTreeSum(TreeNode root) {
		if (root == null) {
			return new int[0];
		}

		sum2count = new HashMap<Integer, Integer>();

		search(root);

		int maxCount = sum2count.values().stream().mapToInt(x -> x).max().getAsInt();
		return sum2count.keySet().stream().filter(sum -> sum2count.get(sum) == maxCount).mapToInt(x -> x).toArray();
	}

	int search(TreeNode node) {
		if (node == null) {
			return 0;
		}

		int sum = node.val;
		sum += search(node.left);
		sum += search(node.right);

		sum2count.put(sum, sum2count.getOrDefault(sum, 0) + 1);

		return sum;
	}
}
