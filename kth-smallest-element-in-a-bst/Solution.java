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
	static int result;
	static int sequence;

	public int kthSmallest(TreeNode root, int k) {
		sequence = 0;
		search(root, k);
		return result;
	}

	void search(TreeNode node, int k) {
		if (node == null || sequence == k) {
			return;
		}

		search(node.left, k);
		sequence++;
		if (sequence == k) {
			result = node.val;
		}
		search(node.right, k);
	}
}
