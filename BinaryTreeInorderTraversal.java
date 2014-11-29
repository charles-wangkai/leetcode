import java.util.ArrayList;
import java.util.List;
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

public class BinaryTreeInorderTraversal {
	public List<Integer> inorderTraversal(TreeNode root) {
		Stack<Object> stack = new Stack<Object>();
		stack.push(root);
		List<Integer> values = new ArrayList<Integer>();
		while (!stack.empty()) {
			Object top = stack.pop();
			if (top == null) {
				continue;
			}
			if (top instanceof TreeNode) {
				TreeNode node = (TreeNode) top;
				stack.push(node.right);
				stack.push(node.val);
				stack.push(node.left);
			} else {
				int value = (Integer) top;
				values.add(value);
			}
		}
		return values;
	}
}
