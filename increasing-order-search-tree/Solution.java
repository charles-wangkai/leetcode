import java.util.ArrayList;
import java.util.List;

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
	public TreeNode increasingBST(TreeNode root) {
		List<TreeNode> nodes = new ArrayList<>();
		inorderSearch(root, nodes);

		for (int i = 0; i < nodes.size(); i++) {
			TreeNode node = nodes.get(i);

			node.left = null;
			node.right = (i + 1 == nodes.size()) ? null : nodes.get(i + 1);
		}
		return nodes.get(0);
	}

	void inorderSearch(TreeNode node, List<TreeNode> nodes) {
		if (node.left != null) {
			inorderSearch(node.left, nodes);
		}
		nodes.add(node);
		if (node.right != null) {
			inorderSearch(node.right, nodes);
		}
	}
}
