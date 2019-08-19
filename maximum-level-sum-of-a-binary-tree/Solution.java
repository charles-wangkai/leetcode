import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Solution {
	public int maxLevelSum(TreeNode root) {
		List<Integer> sums = new ArrayList<>();
		search(sums, root, 0);

		int maxSum = sums.stream().mapToInt(x -> x).max().getAsInt();
		return IntStream.range(0, sums.size()).filter(i -> sums.get(i) == maxSum).findFirst().getAsInt() + 1;
	}

	void search(List<Integer> sums, TreeNode node, int index) {
		if (index == sums.size()) {
			sums.add(0);
		}

		sums.set(index, sums.get(index) + node.val);

		if (node.left != null) {
			search(sums, node.left, index + 1);
		}
		if (node.right != null) {
			search(sums, node.right, index + 1);
		}
	}
}
