import java.util.ArrayList;
import java.util.List;

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
	public List<List<String>> printTree(TreeNode root) {
		int height = findHeight(root);
		int width = findWidth(root);

		List<List<String>> output = new ArrayList<List<String>>();
		for (int i = 0; i < height; i++) {
			List<String> row = new ArrayList<String>();
			for (int j = 0; j < width; j++) {
				row.add("");
			}
			output.add(row);
		}

		fill(output, root, 0, 0, width - 1);

		return output;
	}

	void fill(List<List<String>> output, TreeNode node, int row, int columnLower, int columnUpper) {
		if (node == null) {
			return;
		}

		int columnMiddle = (columnLower + columnUpper) / 2;
		output.get(row).set(columnMiddle, String.valueOf(node.val));
		fill(output, node.left, row + 1, columnLower, columnMiddle - 1);
		fill(output, node.right, row + 1, columnMiddle + 1, columnUpper);
	}

	int findHeight(TreeNode node) {
		if (node == null) {
			return 0;
		}

		return 1 + Math.max(findHeight(node.left), findHeight(node.right));
	}

	int findWidth(TreeNode node) {
		if (node == null) {
			return 0;
		}

		return 1 + Math.max(findWidth(node.left), findWidth(node.right)) * 2;
	}
}
