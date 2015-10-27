import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class SerializeAndDeserializeBinaryTree {
	private static final String DELIMITER = ",";
	private static final String NULL = "null";

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		List<String> sequence = new ArrayList<String>();

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode head = queue.poll();
			sequence.add(head == null ? NULL : (head.val + ""));

			if (head != null) {
				queue.offer(head.left);
				queue.offer(head.right);
			}
		}

		return String.join(DELIMITER, sequence);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		String[] values = data.split(DELIMITER);

		TreeNode root = buildTreeNode(values[0]);
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		for (int i = 1; i < values.length; i += 2) {
			TreeNode leftChild = buildTreeNode(values[i]);
			TreeNode rightChild = buildTreeNode(values[i + 1]);

			TreeNode parent = queue.poll();
			parent.left = leftChild;
			parent.right = rightChild;

			if (leftChild != null) {
				queue.offer(leftChild);
			}
			if (rightChild != null) {
				queue.offer(rightChild);
			}
		}

		return root;
	}

	private TreeNode buildTreeNode(String value) {
		return value.equals(NULL) ? null : (new TreeNode(Integer.parseInt(value + "")));
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));