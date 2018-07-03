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
	int consecutiveLength;

	public int longestConsecutive(TreeNode root) {
		consecutiveLength = 0;
		search(root);
		return consecutiveLength;
	}

	Element search(TreeNode node) {
		if (node == null) {
			return new Element(0, 0);
		}

		Element result = new Element(1, 1);
		updateResult(result, node, node.left);
		updateResult(result, node, node.right);

		consecutiveLength = Math.max(consecutiveLength, result.increasingLength + result.decreasingLength - 1);

		return result;
	}

	void updateResult(Element result, TreeNode node, TreeNode child) {
		if (child == null) {
			return;
		}

		Element subResult = search(child);
		if (node.val + 1 == child.val) {
			result.increasingLength = Math.max(result.increasingLength, subResult.increasingLength + 1);
		} else if (node.val - 1 == child.val) {
			result.decreasingLength = Math.max(result.decreasingLength, subResult.decreasingLength + 1);
		}
	}
}

class Element {
	int increasingLength;
	int decreasingLength;

	Element(int increasingLength, int decreasingLength) {
		this.increasingLength = increasingLength;
		this.decreasingLength = decreasingLength;
	}
}