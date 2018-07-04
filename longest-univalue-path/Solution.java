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
	int maxPath;

	public int longestUnivaluePath(TreeNode root) {
		maxPath = 0;
		search(root);
		return maxPath;
	}

	int search(TreeNode node) {
		if (node == null) {
			return -1;
		}

		int leftPath = search(node.left);
		if (node.left == null || node.left.val != node.val) {
			leftPath = 0;
		}

		int rightPath = search(node.right);
		if (node.right == null || node.right.val != node.val) {
			rightPath = 0;
		}

		maxPath = Math.max(maxPath, leftPath + rightPath);
		return 1 + Math.max(leftPath, rightPath);
	}
}
