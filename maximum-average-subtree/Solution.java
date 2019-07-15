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
	double maxAvg;

	public double maximumAverageSubtree(TreeNode root) {
		maxAvg = -1;
		search(root);

		return maxAvg;
	}

	Element search(TreeNode node) {
		if (node == null) {
			return new Element(0, 0);
		}

		int nodeNum = 1;
		int valueSum = node.val;

		Element leftResult = search(node.left);
		nodeNum += leftResult.nodeNum;
		valueSum += leftResult.valueSum;

		Element rightResult = search(node.right);
		nodeNum += rightResult.nodeNum;
		valueSum += rightResult.valueSum;

		maxAvg = Math.max(maxAvg, (double) valueSum / nodeNum);

		return new Element(nodeNum, valueSum);
	}
}

class Element {
	int nodeNum;
	int valueSum;

	Element(int nodeNum, int valueSum) {
		this.nodeNum = nodeNum;
		this.valueSum = valueSum;
	}
}