import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Codec {
	private static final String DELIMITER = ",";

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null) {
			return "";
		}

		return concat(String.valueOf(root.val), concat(serialize(root.left), serialize(root.right)));
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data.isEmpty()) {
			return null;
		}

		int[] values = Arrays.stream(data.split(DELIMITER)).mapToInt(Integer::parseInt).toArray();

		int rootValue = values[0];
		List<Integer> leftValues = new ArrayList<Integer>();
		List<Integer> rightValues = new ArrayList<Integer>();
		for (int i = 1; i < values.length; i++) {
			if (values[i] < rootValue) {
				leftValues.add(values[i]);
			} else {
				rightValues.add(values[i]);
			}
		}

		TreeNode root = new TreeNode(rootValue);
		root.left = deserialize(convertToString(leftValues));
		root.right = deserialize(convertToString(rightValues));
		return root;
	}

	private String concat(String s1, String s2) {
		if (s1.isEmpty() || s2.isEmpty()) {
			return s1 + s2;
		} else {
			return s1 + DELIMITER + s2;
		}
	}

	private String convertToString(List<Integer> values) {
		return String.join(DELIMITER, values.stream().map(Object::toString).collect(Collectors.toList()));
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));