// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class InvertBinaryTree {
	public TreeNode invertTree(TreeNode root) {
		if (root != null) {
			TreeNode temp = root.left;
			root.left = root.right;
			root.right = temp;

			invertTree(root.left);
			invertTree(root.right);
		}
		return root;
	}
}
