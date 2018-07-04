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
	public int findSecondMinimumValue(TreeNode root) {
		int result = search(root.val, root);
		return (result == Integer.MAX_VALUE) ? -1 : result;
	}

	int search(int minValue, TreeNode node) {
		if (node == null) {
			return Integer.MAX_VALUE;
		}

		if (node.val != minValue) {
			return node.val;
		}

		return Math.min(search(minValue, node.left), search(minValue, node.right));
	}
}
