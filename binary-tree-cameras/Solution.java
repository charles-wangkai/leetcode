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
	int cameraNum;

	public int minCameraCover(TreeNode root) {
		cameraNum = 0;
		search(root, false);
		return cameraNum;
	}

	int search(TreeNode node, boolean hasParent) {
		if (node == null) {
			return 2;
		}

		int leftDistance = search(node.left, true);
		int rightDistance = search(node.right, true);

		if (leftDistance == 3 || rightDistance == 3) {
			cameraNum++;

			return 1;
		} else if (Math.min(leftDistance, rightDistance) == 1) {
			return 2;
		} else {
			if (hasParent) {
				return 3;
			} else {
				cameraNum++;

				return 1;
			}
		}
	}
}
