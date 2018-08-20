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
	public TreeNode constructFromPrePost(int[] pre, int[] post) {
		return build(pre, 0, pre.length - 1, post, 0, post.length - 1);
	}

	TreeNode build(int[] pre, int preBeginIndex, int preEndIndex, int[] post, int postBeginIndex, int postEndIndex) {
		if (preBeginIndex > preEndIndex) {
			return null;
		}

		TreeNode node = new TreeNode(pre[preBeginIndex]);
		if (preBeginIndex < preEndIndex) {
			int leftPreBeginIndex = preBeginIndex + 1;

			int pos = search(post, postBeginIndex, pre[leftPreBeginIndex]);
			int leftLength = pos - postBeginIndex + 1;

			node.left = build(pre, leftPreBeginIndex, leftPreBeginIndex + leftLength - 1, post, postBeginIndex, pos);
			node.right = build(pre, leftPreBeginIndex + leftLength, preEndIndex, post, pos + 1, postEndIndex - 1);
		}
		return node;
	}

	int search(int[] a, int beginIndex, int target) {
		for (int i = beginIndex;; i++) {
			if (a[i] == target) {
				return i;
			}
		}
	}
}
