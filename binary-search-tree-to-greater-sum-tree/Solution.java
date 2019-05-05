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
	int sum;

	public TreeNode bstToGst(TreeNode root) {
		sum = 0;
		modify(root);
		return root;
	}

	void modify(TreeNode node) {
		if (node == null) {
			return;
		}

		modify(node.right);

		sum += node.val;
		node.val = sum;

		modify(node.left);
	}
}
