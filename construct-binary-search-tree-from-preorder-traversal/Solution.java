import java.util.Arrays;

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
	public TreeNode bstFromPreorder(int[] preorder) {
		if (preorder.length == 0) {
			return null;
		}

		TreeNode node = new TreeNode(preorder[0]);

		int endIndex = 1;
		while (endIndex < preorder.length && preorder[endIndex] < preorder[0]) {
			endIndex++;
		}

		node.left = bstFromPreorder(Arrays.copyOfRange(preorder, 1, endIndex));
		node.right = bstFromPreorder(Arrays.copyOfRange(preorder, endIndex, preorder.length));

		return node;
	}
}
