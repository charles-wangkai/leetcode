import java.util.Stack;

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
	public TreeNode UpsideDownBinaryTree(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		for (TreeNode node = root; node != null; node = node.left) {
			stack.push(node);
		}

		TreeNode result = null;
		while (!stack.empty()) {
			TreeNode node = stack.pop();
			if (result == null) {
				result = node;
			}
			if (stack.empty()) {
				node.left = null;
				node.right = null;
			} else {
				node.left = stack.peek().right;
				node.right = stack.peek();
			}
		}

		return result;
	}
}
