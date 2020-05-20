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
	int kthMin;
	int sequence;

	public int kthSmallest(TreeNode root, int k) {
		sequence = 0;
		search(root, k);

		return kthMin;
	}

	void search(TreeNode node, int k) {
		if (node == null || sequence == k) {
			return;
		}

		search(node.left, k);

		++sequence;
		if (sequence == k) {
			kthMin = node.val;
		}

		search(node.right, k);
	}
}
