// Definition for binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Solution {
	int result = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {
		search(root);
		return result;
	}

	int search(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int leftMaxPathSum = search(node.left);
		int rightMaxPathSum = search(node.right);
		result = Math.max(result, node.val + leftMaxPathSum + rightMaxPathSum);
		return Math
				.max(0, node.val + Math.max(leftMaxPathSum, rightMaxPathSum));
	}
}
