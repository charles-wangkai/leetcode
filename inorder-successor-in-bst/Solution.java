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
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		TreeNode successor;
		if (p.right == null) {
			Stack<TreeNode> path = new Stack<TreeNode>();
			searchPath(root, p, path);
			TreeNode node = path.pop();
			while (!path.empty() && path.peek().left != node) {
				node = path.pop();
			}
			successor = path.empty() ? null : path.peek();
		} else {
			successor = p.right;
			while (successor.left != null) {
				successor = successor.left;
			}
		}
		return successor;
	}

	void searchPath(TreeNode node, TreeNode p, Stack<TreeNode> path) {
		path.push(node);
		if (path.peek() != p && node.left != null) {
			searchPath(node.left, p, path);
		}
		if (path.peek() != p && node.right != null) {
			searchPath(node.right, p, path);
		}
		if (path.peek() != p) {
			path.pop();
		}
	}
}
