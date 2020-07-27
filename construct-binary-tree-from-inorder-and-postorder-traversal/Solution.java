import java.util.stream.IntStream;

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

class Solution {
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
	}

	TreeNode buildTree(int[] inorder, int inBegin, int inEnd, int[] postorder, int postBegin, int postEnd) {
		if (inBegin > inEnd) {
			return null;
		}

		int rootValue = postorder[postEnd];
		int inRoot = IntStream.rangeClosed(inBegin, inEnd).filter(i -> inorder[i] == rootValue).findAny().getAsInt();
		int leftLength = inRoot - inBegin;
		int rightLength = inEnd - inRoot;

		return new TreeNode(rootValue,
				buildTree(inorder, inBegin, inRoot - 1, postorder, postBegin, postBegin + leftLength - 1),
				buildTree(inorder, inRoot + 1, inEnd, postorder, postEnd - rightLength, postEnd - 1));
	}
}