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
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return buildTree(preorder, 0, preorder.length - 1, inorder, 0,
				inorder.length - 1);
	}

	TreeNode buildTree(int[] preorder, int beginPre, int endPre, int[] inorder,
			int beginIn, int endIn) {
		if (beginPre > endPre) {
			return null;
		}
		int rootValue = preorder[beginPre];
		int rootIn = findValue(inorder, beginIn, endIn, rootValue);
		int leftLen = rootIn - beginIn;
		int rightLen = endIn - rootIn;
		TreeNode root = new TreeNode(rootValue);
		root.left = buildTree(preorder, beginPre + 1, beginPre + leftLen,
				inorder, beginIn, rootIn - 1);
		root.right = buildTree(preorder, endPre - rightLen + 1, endPre,
				inorder, rootIn + 1, endIn);
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
