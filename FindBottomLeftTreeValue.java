// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class FindBottomLeftTreeValue {
	int maxDepth;
	int leftValueAtMaxDepth;

	public int findBottomLeftValue(TreeNode root) {
		maxDepth = -1;

		search(root, 0);

		return leftValueAtMaxDepth;
	}

	void search(TreeNode node, int depth) {
		if (node == null) {
			return;
		}

		search(node.left, depth + 1);

		if (depth > maxDepth) {
			maxDepth = depth;
			leftValueAtMaxDepth = node.val;
		}

		search(node.right, depth + 1);
	}
}
