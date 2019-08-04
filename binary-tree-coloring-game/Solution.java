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
	public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
		if (root == null) {
			return false;
		}

		if (root.val == x) {
			int leftSubtreeSize = computeSubtreeSize(root.left);
			int rightSubtreeSize = computeSubtreeSize(root.right);

			return 2 * leftSubtreeSize > n || 2 * rightSubtreeSize > n
					|| 2 * (n - 1 - leftSubtreeSize - rightSubtreeSize) > n;
		}

		return btreeGameWinningMove(root.left, n, x) || btreeGameWinningMove(root.right, n, x);
	}

	int computeSubtreeSize(TreeNode root) {
		return (root == null) ? 0 : (1 + computeSubtreeSize(root.left) + computeSubtreeSize(root.right));
	}
}
