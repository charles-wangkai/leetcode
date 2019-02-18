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
	public boolean isCousins(TreeNode root, int x, int y) {
		Location locationX = search(root, x, 0);
		Location locationY = search(root, y, 0);

		return locationX.depth == locationY.depth && locationX.parent != locationY.parent;
	}

	Location search(TreeNode node, int target, int depth) {
		if (node == null) {
			return null;
		}

		if (node.val == target) {
			return new Location(depth, null);
		}

		if ((node.left != null && node.left.val == target) || (node.right != null && node.right.val == target)) {
			return new Location(depth + 1, node);
		}

		Location leftSubResult = search(node.left, target, depth + 1);
		if (leftSubResult != null) {
			return leftSubResult;
		} else {
			return search(node.right, target, depth + 1);
		}
	}
}

class Location {
	int depth;
	TreeNode parent;

	Location(int depth, TreeNode parent) {
		this.depth = depth;
		this.parent = parent;
	}
}