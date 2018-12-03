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
	public boolean flipEquiv(TreeNode root1, TreeNode root2) {
		regulate(root1);
		regulate(root2);

		return isEqual(root1, root2);
	}

	static void regulate(TreeNode node) {
		if (node == null) {
			return;
		}

		if (node.right != null && (node.left == null || node.left.val > node.right.val)) {
			TreeNode temp = node.left;
			node.left = node.right;
			node.right = temp;
		}

		regulate(node.left);
		regulate(node.right);
	}

	static boolean isEqual(TreeNode node1, TreeNode node2) {
		if (node1 == null) {
			if (node2 == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (node2 == null) {
				return false;
			} else {
				return node1.val == node2.val && isEqual(node1.left, node2.left) && isEqual(node1.right, node2.right);
			}
		}
	}
}
