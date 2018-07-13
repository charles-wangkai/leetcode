import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	public List<List<Integer>> levelOrder(Node root) {
		if (root == null) {
			return Collections.emptyList();
		}

		List<List<Integer>> result = new ArrayList<>();
		search(result, root, 0);
		return result;
	}

	void search(List<List<Integer>> result, Node node, int depth) {
		if (depth == result.size()) {
			result.add(new ArrayList<>());
		}
		result.get(depth).add(node.val);

		for (Node child : node.children) {
			search(result, child, depth + 1);
		}
	}
}
