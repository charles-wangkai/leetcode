import java.util.Stack;

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
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		TreeNode root = null;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		for (int num : nums) {
			TreeNode node = new TreeNode(num);

			while (!stack.empty() && num > stack.peek().val) {
				stack.pop();
			}

			if (stack.empty()) {
				node.left = root;
				root = node;
			} else {
				node.left = stack.peek().right;
				stack.peek().right = node;
			}
			stack.push(node);
		}
		return root;
	}
}
