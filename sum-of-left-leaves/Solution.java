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

		if (node.left == null && node.right == null) {
			return direction == Direction.LEFT ? node.val : 0;
		}

		return (node.left == null ? 0 : search(node.left, Direction.LEFT))
				+ (node.right == null ? 0 : search(node.right, Direction.RIGHT));
	}
}
