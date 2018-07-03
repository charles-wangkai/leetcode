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
	int longestLength;

	public int longestConsecutive(TreeNode root) {
		longestLength = 0;
		search(root, 0, 0);
		return longestLength;
	}

	void search(TreeNode node, int parentValue, int parentLength) {
		if (node == null) {
			return;
		}

		int length;
		if (parentLength > 0 && node.val == parentValue + 1) {
			length = parentLength + 1;
		} else {
			length = 1;
		}
		longestLength = Math.max(longestLength, length);

		search(node.left, node.val, length);
		search(node.right, node.val, length);
	}
}
