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
	public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
		if (root1 == null) {
			return false;
		}

		if (find(root2, target - root1.val)) {
			return true;
		}

		return twoSumBSTs(root1.left, root2, target) || twoSumBSTs(root1.right, root2, target);
	}

	boolean find(TreeNode root2, int value) {
		if (root2 == null) {
			return false;
		}

		if (root2.val < value) {
			return find(root2.right, value);
		} else if (root2.val > value) {
			return find(root2.left, value);
		}

		return true;
	}
}
