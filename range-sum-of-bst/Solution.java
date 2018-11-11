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
	public int rangeSumBST(TreeNode root, int L, int R) {
		return search(root, Integer.MIN_VALUE, Integer.MAX_VALUE, L, R);
	}

	int search(TreeNode node, int lower, int upper, int L, int R) {
		if (node == null || upper < L || lower > R) {
			return 0;
		}

		int result = 0;
		if (node.val >= L && node.val <= R) {
			result += node.val;
		}
		result += search(node.left, lower, node.val - 1, L, R);
		result += search(node.right, node.val + 1, upper, L, R);
		return result;
	}
}
