import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

// Definition for a Node.
class Node {
	public int val;
	public List<Node> children;

	public Node() {
	}

	public Node(int _val, List<Node> _children) {
		val = _val;
		children = _children;
	}
}

public class Solution {
	public List<Integer> postorder(Node root) {
		if (root == null) {
			return Collections.emptyList();
		}

		List<Integer> result = new ArrayList<>();
		Stack<Object> stack = new Stack<>();
		stack.push(root);
		while (!stack.empty()) {
			Object top = stack.pop();

			if (top instanceof Node) {
				Node node = (Node) top;
				stack.push(node.val);
				for (int i = node.children.size() - 1; i >= 0; i--) {
					stack.push(node.children.get(i));
				}
			} else {
				result.add((Integer) top);
			}
		}
		return result;
	}
}
