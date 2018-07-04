//Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Solution {
	public int largestBSTSubtree(TreeNode root) {
		return search(root).bstSize;
	}

	Result search(TreeNode node) {
		if (node == null) {
			return new Result(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
		}

		Result leftResult = search(node.left);
		Result rightResult = search(node.right);
		if (leftResult.bst && rightResult.bst && leftResult.max <= node.val && rightResult.min >= node.val) {
			return new Result(true, leftResult.bstSize + rightResult.bstSize + 1, Math.min(node.val, leftResult.min),
					Math.max(node.val, rightResult.max));
		} else {
			return new Result(false, Math.max(leftResult.bstSize, rightResult.bstSize), 0, 0);
		}
	}
}

class Result {
	boolean bst;
	int bstSize;
	int min;
	int max;

	Result(boolean bst, int bstSize, int min, int max) {
		super();
		this.bst = bst;
		this.bstSize = bstSize;
		this.min = min;
		this.max = max;
	}
}