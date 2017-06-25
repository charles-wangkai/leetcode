// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class AddOneRowToTree {
	public TreeNode addOneRow(TreeNode root, int v, int d) {
		if (d == 1) {
			TreeNode result = new TreeNode(v);
			result.left = root;

			return result;
		} else {
			insert(root, v, d, 2);

			return root;
		}
	}

	void insert(TreeNode node, int v, int d, int depth) {
		if (depth == d) {
			TreeNode leftNode = new TreeNode(v);
			leftNode.left = node.left;
			node.left = leftNode;

			TreeNode rightNode = new TreeNode(v);
			rightNode.right = node.right;
			node.right = rightNode;

			return;
		}

		if (node.left != null) {
			insert(node.left, v, d, depth + 1);
		}
		if (node.right != null) {
			insert(node.right, v, d, depth + 1);
		}
	}
}
