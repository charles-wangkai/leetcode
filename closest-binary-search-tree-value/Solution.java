class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Solution {
	public int closestValue(TreeNode root, double target) {
		Integer closest = null;
		TreeNode node = root;
		while (node != null) {
			if (closest == null
					|| Math.abs(node.val - target) < Math.abs(closest - target)) {
				closest = node.val;
			}

			if (node.val <= target) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		return closest;
	}
}
