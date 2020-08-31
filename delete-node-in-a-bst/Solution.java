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

				return new TreeNode(mostRightInLeft.val, deleteNode(root.left, mostRightInLeft.val), root.right);
			} else if (root.right != null) {
				TreeNode mostLeftInRight = root.right;
				while (mostLeftInRight.left != null) {
					mostLeftInRight = mostLeftInRight.left;
				}

				return new TreeNode(mostLeftInRight.val, root.left, deleteNode(root.right, mostLeftInRight.val));
			} else {
				return null;
			}
		}

		if (key < root.val) {
			root.left = deleteNode(root.left, key);
		} else {
			root.right = deleteNode(root.right, key);
		}

		return root;
	}
}
