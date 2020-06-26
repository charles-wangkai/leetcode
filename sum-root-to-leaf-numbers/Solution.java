// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

class Solution {
	public int sumNumbers(TreeNode root) {
		return search(root, 0);
	}

	int search(TreeNode node, int prev) {
		if (node == null) {
			return 0;
		}

		int current = prev * 10 + node.val;

		if (node.left == null && node.right == null) {
			return current;
		}

		return search(node.left, current) + search(node.right, current);
	}
}
