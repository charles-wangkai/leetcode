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
	public int maxDepth(Node root) {
		if (root == null) {
			return 0;
		}
		if (root.children.isEmpty()) {
			return 1;
		}

		return root.children.stream().mapToInt(this::maxDepth).max().getAsInt() + 1;
	}
}
