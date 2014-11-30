// Definition for binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class BalancedBinaryTree {
	public boolean isBalanced(TreeNode root) {
		return findDepth(root) >= 0;
	}

	int findDepth(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int depthLeft = findDepth(node.left);
		int depthRight = findDepth(node.right);
		if (depthLeft < 0 || depthRight < 0
				|| Math.abs(depthLeft - depthRight) > 1) {
			return -1;
		}
		return 1 + Math.max(depthLeft, depthRight);
	}
}
