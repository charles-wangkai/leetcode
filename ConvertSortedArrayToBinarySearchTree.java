// Definition for binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class ConvertSortedArrayToBinarySearchTree {
	public TreeNode sortedArrayToBST(int[] num) {
		return sortedArrayToBST(num, 0, num.length - 1);
	}

	TreeNode sortedArrayToBST(int[] num, int begin, int end) {
		if (begin > end) {
			return null;
		}
		int middle = (begin + end) / 2;
		TreeNode root = new TreeNode(num[middle]);
		root.left = sortedArrayToBST(num, begin, middle - 1);
		root.right = sortedArrayToBST(num, middle + 1, end);
		return root;
	}
}
