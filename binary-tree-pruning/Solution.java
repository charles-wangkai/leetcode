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
	public TreeNode pruneTree(TreeNode root) {
		if (root == null) {
			return null;
		}

		root.left = pruneTree(root.left);
		root.right = pruneTree(root.right);

		if (root.left == null && root.right == null && root.val == 0) {
			return null;
		} else {
			return root;
		}
	}
}
