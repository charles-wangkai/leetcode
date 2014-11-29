// Definition for binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class RecoverBinarySearchTree {
	TreeNode first;
	TreeNode second;
	TreeNode prev;

	public void recoverTree(TreeNode root) {
		inorder(root);

		int temp = first.val;
		first.val = second.val;
		second.val = temp;
	}

	void inorder(TreeNode node) {
		if (node == null) {
			return;
		}
		inorder(node.left);
		if (first == null && prev != null && node.val < prev.val) {
			first = prev;
		}
		if (first != null && node.val <= first.val) {
			second = node;
		}
		prev = node;
		inorder(node.right);
	}
}
