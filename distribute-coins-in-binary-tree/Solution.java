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
	int moveNum;

	public int distributeCoins(TreeNode root) {
		moveNum = 0;
		search(root);
		return moveNum;
	}

	int search(TreeNode node) {
		if (node == null) {
			return 0;
		}

		node.val += search(node.left) + search(node.right);

		int result = node.val - 1;
		moveNum += Math.abs(result);
		return result;
	}
}
