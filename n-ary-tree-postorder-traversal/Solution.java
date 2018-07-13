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
		search(result, root, new Stack<>());
		return result;
	}

	void search(List<Integer> result, Node node, Stack<Integer> stack) {
		stack.push(node.val);

		for (Node child : node.children) {
			search(result, child, stack);
		}

		result.add(stack.pop());
	}
}
