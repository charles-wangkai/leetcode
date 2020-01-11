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
	public int sumEvenGrandparent(TreeNode root) {
		return search(root, null, null);
	}

	int search(TreeNode node, TreeNode parent, TreeNode grandparent) {
		if (node == null) {
			return 0;
		}

		return search(node.left, node, parent) + search(node.right, node, parent)
				+ ((grandparent != null && grandparent.val % 2 == 0) ? node.val : 0);
	}
}
