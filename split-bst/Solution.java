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
	public TreeNode[] splitBST(TreeNode root, int V) {
		if (root == null) {
			return new TreeNode[2];
		}

		if (root.val <= V) {
			TreeNode[] rightSubResult = splitBST(root.right, V);
			root.right = rightSubResult[0];

			return new TreeNode[] { root, rightSubResult[1] };
		} else {
			TreeNode[] leftSubResult = splitBST(root.left, V);
			root.left = leftSubResult[1];

			return new TreeNode[] { leftSubResult[0], root };
		}
	}
}
