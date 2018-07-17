import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

public class Codec {
	// Encodes a tree to a single string.
	public String serialize(Node root) {
		if (root == null) {
			return "";
		}

		return String.format("%d[%s]", root.val,
				root.children.stream().map(this::serialize).collect(Collectors.joining(",")));
	}

	// Decodes your encoded data to tree.
	public Node deserialize(String data) {
		if (data.isEmpty()) {
			return null;
		}

		int bracketBeginIndex = data.indexOf('[');
		int value = Integer.parseInt(data.substring(0, bracketBeginIndex));
		Node node = new Node(value, new ArrayList<>());

		int beginIndex = bracketBeginIndex + 1;
		int depth = 0;
		for (int endIndex = beginIndex; endIndex < data.length() - 1; endIndex++) {
			char ch = data.charAt(endIndex);
			if (ch == '[') {
				depth++;
			} else if (ch == ']') {
				depth--;

				if (depth == 0) {
					node.children.add(deserialize(data.substring(beginIndex, endIndex + 1)));

					beginIndex = endIndex + 2;
				}
			}
		}

		return node;
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));