//Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Solution {
	public TreeNode deleteNode(TreeNode root, int key) {
		if (root == null) {
			return null;
		}

		if (root.val == key) {
			if (root.left != null) {
				TreeNode mostRightInLeft = root.left;
				while (mostRightInLeft.right != null) {
					mostRightInLeft = mostRightInLeft.right;
				}

				TreeNode newRoot = new TreeNode(mostRightInLeft.val);
				newRoot.left = deleteNode(root.left, mostRightInLeft.val);
				newRoot.right = root.right;
				return newRoot;
			} else if (root.right != null) {
				TreeNode mostLeftInRight = root.right;
				while (mostLeftInRight.left != null) {
					mostLeftInRight = mostLeftInRight.left;
				}

				TreeNode newRoot = new TreeNode(mostLeftInRight.val);
				newRoot.left = root.left;
				newRoot.right = deleteNode(root.right, mostLeftInRight.val);
				return newRoot;
			} else {
				return null;
			}
		} else {
			if (root.val < key) {
				root.right = deleteNode(root.right, key);
			} else {
				root.left = deleteNode(root.left, key);
			}
			return root;
		}
	}
}
