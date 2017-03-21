// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class ConvertBSTToGreaterTree {
	int greaterSum;

	public TreeNode convertBST(TreeNode root) {
		greaterSum = 0;

		search(root);

		return root;
	}

	void search(TreeNode node) {
		if (node == null) {
			return;
		}

		search(node.right);

		node.val += greaterSum;
		greaterSum = node.val;

		search(node.left);
	}
}
