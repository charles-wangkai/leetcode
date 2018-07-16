import java.util.ArrayList;
import java.util.List;

// Definition for a Node.
class Node {
	public int val;
	public Node left;
	public Node right;

	public Node() {
	}

	public Node(int _val, Node _left, Node _right) {
		val = _val;
		left = _left;
		right = _right;
	}
}

public class Solution {
	public Node treeToDoublyList(Node root) {
		if (root == null) {
			return null;
		}

		List<Node> nodes = new ArrayList<>();
		inorder(nodes, root);

		for (int i = 0; i < nodes.size(); i++) {
			Node leftNode = nodes.get(i);
			Node rightNode = nodes.get((i + 1) % nodes.size());

			leftNode.right = rightNode;
			rightNode.left = leftNode;
		}
		return nodes.get(0);
	}

	void inorder(List<Node> nodes, Node node) {
		if (node == null) {
			return;
		}

		inorder(nodes, node.left);
		nodes.add(node);
		inorder(nodes, node.right);
	}
}
