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

public class Solution {
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		Stack<Object> stack = new Stack<Object>();
		stack.push(root);
		while (!stack.empty()) {
			Object element = stack.pop();
			if (element == null) {
				continue;
			}
			if (element instanceof Integer) {
				int value = (Integer) element;
				result.add(value);
			} else {
				TreeNode node = (TreeNode) element;
				stack.push(node.val);
				stack.push(node.right);
				stack.push(node.left);
			}
		}
		return result;
	}
}
