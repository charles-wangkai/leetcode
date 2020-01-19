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
	public TreeNode removeLeafNodes(TreeNode root, int target) {
		if (root == null) {
			return null;
		}

		root.left = removeLeafNodes(root.left, target);
		root.right = removeLeafNodes(root.right, target);

		if (root.left == null && root.right == null && root.val == target) {
			return null;
		}

		return root;
	}
}
