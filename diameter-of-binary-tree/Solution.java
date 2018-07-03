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
	int diameter;

	public int diameterOfBinaryTree(TreeNode root) {
		diameter = 0;

		search(root);

		return diameter;
	}

	int search(TreeNode node) {
		if (node == null) {
			return 0;
		}

		int leftHeight = search(node.left);
		int rightHeight = search(node.right);

		diameter = Math.max(diameter, leftHeight + rightHeight);

		return Math.max(leftHeight, rightHeight) + 1;
	}
}
