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

public class BinarySearchTreeIterator {
	private Stack<Object> stack = new Stack<Object>();

	public BinarySearchTreeIterator(TreeNode root) {
		stack.push(root);
		expandToMin();
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !stack.empty();
	}

	/** @return the next smallest number */
	public int next() {
		int result = (int) stack.pop();
		expandToMin();
		return result;
	}

	private void expandToMin() {
		while (!stack.empty() && !(stack.peek() instanceof Integer)) {
			Object top = stack.pop();
			if (top != null) {
				TreeNode node = (TreeNode) top;
				stack.push(node.right);
				stack.push(node.val);
				stack.push(node.left);
			}
		}
	}
}

// Your BSTIterator will be called like this:
// BSTIterator i = new BSTIterator(root);
// while (i.hasNext()) v[f()] = i.next();
