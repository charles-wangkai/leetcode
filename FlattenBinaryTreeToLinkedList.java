// Definition for binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class FlattenBinaryTreeToLinkedList {
	TreeNode prev = null;

	public void flatten(TreeNode root) {
		if (root == null) {
			return;
		}
		if (prev != null) {
			prev.left = null;
			prev.right = root;
		}
		prev = root;
		TreeNode right = root.right;
		flatten(root.left);
		flatten(right);
	}
}
