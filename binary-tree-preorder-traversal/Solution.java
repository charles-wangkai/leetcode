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
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> nodeStack = new Stack<TreeNode>();
		nodeStack.push(root);
		while (!nodeStack.empty()) {
			TreeNode node = nodeStack.pop();
			if (node == null) {
				continue;
			}
			result.add(node.val);
			nodeStack.push(node.right);
			nodeStack.push(node.left);
		}
		return result;
	}
}
