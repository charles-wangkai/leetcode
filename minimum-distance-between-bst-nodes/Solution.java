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
	public int minDiffInBST(TreeNode root) {
		List<Integer> values = new ArrayList<Integer>();
		search(root, values);
		return IntStream.range(0, values.size() - 1).map(i -> values.get(i + 1) - values.get(i)).min().getAsInt();
	}

	void search(TreeNode node, List<Integer> values) {
		if (node != null) {
			search(node.left, values);
			values.add(node.val);
			search(node.right, values);
		}
	}
}
