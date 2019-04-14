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
	public int maxAncestorDiff(TreeNode root) {
		return search(root, root.val, root.val);
	}

	int search(TreeNode node, int minAncestor, int maxAncestor) {
		if (node == null) {
			return -1;
		}

		int nextMinAncestor = Math.min(minAncestor, node.val);
		int nextMaxAncestor = Math.max(maxAncestor, node.val);

		return Math.max(Math.max(Math.abs(minAncestor - node.val), Math.abs(maxAncestor - node.val)),
				Math.max(search(node.left, nextMinAncestor, nextMaxAncestor),
						search(node.right, nextMinAncestor, nextMaxAncestor)));
	}
}
