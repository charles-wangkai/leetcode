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
	public int closestValue(TreeNode root, double target) {
		Integer result = null;
		TreeNode node = root;
		while (node != null) {
			if (result == null || Math.abs(node.val - target) < Math.abs(result - target)) {
				result = node.val;
			}

			if (node.val <= target) {
				node = node.right;
			} else {
				node = node.left;
			}
		}

		return result;
	}
}
