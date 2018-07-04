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
	int sum;

	public int sumNumbers(TreeNode root) {
		if (root != null) {
			search(root, 0);
		}
		return sum;
	}

	void search(TreeNode node, int number) {
		int currentNumber = number * 10 + node.val;
		if (node.left == null && node.right == null) {
			sum += currentNumber;
			return;
		}
		if (node.left != null) {
			search(node.left, currentNumber);
		}
		if (node.right != null) {
			search(node.right, currentNumber);
		}
	}
}
