import java.util.HashSet;
import java.util.Set;

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
	public boolean isUnivalTree(TreeNode root) {
		Set<Integer> values = new HashSet<>();
		search(values, root);
		return values.size() == 1;
	}

	void search(Set<Integer> values, TreeNode node) {
		if (node == null) {
			return;
		}

		values.add(node.val);

		search(values, node.left);
		search(values, node.right);
	}
}
