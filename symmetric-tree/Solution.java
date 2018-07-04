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
	public boolean isSymmetric(TreeNode root) {
		Stack<Object> stack1 = createStack(root);
		Stack<Object> stack2 = createStack(root);
		while (!stack1.empty() || !stack2.empty()) {
			if (stack1.empty() || stack2.empty()) {
				return false;
			}
			Object top1 = stack1.pop();
			Object top2 = stack2.pop();
			if (top1 == null && top2 == null) {
				continue;
			}
			if (top1 == null || top2 == null) {
				return false;
			}
			if (!top1.getClass().equals(top2.getClass())) {
				return false;
			}
			if (top1 instanceof Integer) {
				int value1 = (Integer) top1;
				int value2 = (Integer) top2;
				if (value1 != value2) {
					return false;
				}
			} else {
				TreeNode node1 = (TreeNode) top1;
				stack1.push(node1.right);
				stack1.push(node1.val);
				stack1.push(node1.left);

				TreeNode node2 = (TreeNode) top2;
				stack2.push(node2.left);
				stack2.push(node2.val);
				stack2.push(node2.right);
			}
		}
		return true;
	}

	Stack<Object> createStack(TreeNode root) {
		Stack<Object> stack = new Stack<Object>();
		stack.push(root);
		return stack;
	}
}
