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
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		return buildTree(inorder, 0, inorder.length - 1, postorder, 0,
				postorder.length - 1);
	}

	TreeNode buildTree(int[] inorder, int beginIn, int endIn, int[] postorder,
			int beginPost, int endPost) {
		if (beginIn > endIn) {
			return null;
		}
		int rootValue = postorder[endPost];
		int rootIn = findValue(inorder, beginIn, endIn, rootValue);
		int leftLen = rootIn - beginIn;
		int rightLen = endIn - rootIn;
		TreeNode root = new TreeNode(rootValue);
		root.left = buildTree(inorder, beginIn, rootIn - 1, postorder,
				beginPost, beginPost + leftLen - 1);
		root.right = buildTree(inorder, rootIn + 1, endIn, postorder, endPost
				- rightLen, endPost - 1);
		return root;
	}

	int findValue(int[] a, int begin, int end, int target) {
		for (int i = begin; i <= end; i++) {
			if (a[i] == target) {
				return i;
			}
		}
		return -1;
	}
}
