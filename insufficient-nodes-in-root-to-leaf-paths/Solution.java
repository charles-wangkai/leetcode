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
	public TreeNode sufficientSubset(TreeNode root, int limit) {
		return removeInsufficientNodes(root, 0, limit);
	}

	TreeNode removeInsufficientNodes(TreeNode node, int sum, int limit) {
		if (node == null) {
			return null;
		}

		sum += node.val;

		if (node.left == null && node.right == null) {
			return (sum < limit) ? null : node;
		}

		node.left = removeInsufficientNodes(node.left, sum, limit);
		node.right = removeInsufficientNodes(node.right, sum, limit);

		return (node.left == null && node.right == null) ? null : node;
	}
}
