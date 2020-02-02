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
	static final int MODULUS = 1_000_000_007;

	long productMax;

	public int maxProduct(TreeNode root) {
		productMax = -1;
		int total = search(-1, root);
		search(total, root);

		return (int) (productMax % MODULUS);
	}

	int search(int total, TreeNode node) {
		if (node == null) {
			return 0;
		}

		int sum = node.val + search(total, node.left) + search(total, node.right);

		if (total != -1) {
			productMax = Math.max(productMax, (long) sum * (total - sum));
		}

		return sum;
	}
}
