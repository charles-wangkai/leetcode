// Definition for binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class ValidateBinarySearchTree {
	public boolean isValidBST(TreeNode root) {
		return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	boolean isValidBST(TreeNode root, long lower, long upper) {
		if (root == null) {
			return true;
		}
		if (root.val < lower || root.val > upper) {
			return false;
		}
		return isValidBST(root.left, lower, root.val - 1L)
				&& isValidBST(root.right, root.val + 1L, upper);
	}
}
