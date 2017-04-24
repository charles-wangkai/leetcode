// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class BinaryTreeTilt {
	int tiltSum;

	public int findTilt(TreeNode root) {
		tiltSum = 0;
		search(root);
		return tiltSum;
	}

	int search(TreeNode node) {
		if (node == null) {
			return 0;
		}

		int leftSum = search(node.left);
		int rightSum = search(node.right);

		tiltSum += Math.abs(leftSum - rightSum);

		return node.val + leftSum + rightSum;
	}
}
