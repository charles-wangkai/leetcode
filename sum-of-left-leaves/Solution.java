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
	enum Direction {
		LEFT, RIGHT
	};

	public int sumOfLeftLeaves(TreeNode root) {
		return search(root, null);
	}

	int search(TreeNode node, Direction direction) {
		if (node == null) {
			return 0;
		}

		if (node.left == null && node.right == null && direction == Direction.LEFT) {
			return node.val;
		}

		return search(node.left, Direction.LEFT) + search(node.right, Direction.RIGHT);
	}
}
