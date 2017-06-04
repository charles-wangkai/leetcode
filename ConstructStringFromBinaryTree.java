// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class ConstructStringFromBinaryTree {
	public String tree2str(TreeNode t) {
		if (t == null) {
			return "";
		}

		if (t.left == null) {
			if (t.right == null) {
				return String.format("%d", t.val);
			} else {
				return String.format("%d()(%s)", t.val, tree2str(t.right));
			}
		} else {
			if (t.right == null) {
				return String.format("%d(%s)", t.val, tree2str(t.left));
			} else {
				return String.format("%d(%s)(%s)", t.val, tree2str(t.left), tree2str(t.right));
			}
		}
	}
}
