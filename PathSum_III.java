// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class PathSum_III {
	public int pathSum(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}

		return search(root, sum, 0) + pathSum(root.left, sum) + pathSum(root.right, sum);
	}

	int search(TreeNode node, int sum, int current) {
		if (node == null) {
			return 0;
		}

		int result = 0;
		int next = current + node.val;
		if (next == sum) {
			result++;
		}
		result += search(node.left, sum, next);
		result += search(node.right, sum, next);
		return result;
	}
}
