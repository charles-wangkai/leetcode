import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

//Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Solution {
	public boolean isCompleteTree(TreeNode root) {
		List<List<TreeNode>> rows = new ArrayList<>();

		search(rows, 0, root);

		for (int r = 0; r < rows.size() - 1; r++) {
			List<TreeNode> row = rows.get(r);

			if (r == rows.size() - 2) {
				if (IntStream.range(0, row.size() - 1).anyMatch(c -> row.get(c) == null && row.get(c + 1) != null)) {
					return false;
				}
			} else {
				if (row.stream().anyMatch(node -> node == null)) {
					return false;
				}
			}
		}
		return true;
	}

	void search(List<List<TreeNode>> rows, int depth, TreeNode node) {
		if (depth == rows.size()) {
			rows.add(new ArrayList<>());
		}

		rows.get(depth).add(node);

		if (node != null) {
			search(rows, depth + 1, node.left);
			search(rows, depth + 1, node.right);
		}
	}
}
