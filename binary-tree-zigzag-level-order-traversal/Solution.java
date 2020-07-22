import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

class Solution {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> levels = new ArrayList<>();
		search(levels, root, 0);
		for (int i = 1; i < levels.size(); i += 2) {
			Collections.reverse(levels.get(i));
		}

		return levels;
	}

	void search(List<List<Integer>> levels, TreeNode node, int depth) {
		if (node == null) {
			return;
		}

		if (depth == levels.size()) {
			levels.add(new ArrayList<>());
		}
		levels.get(depth).add(node.val);

		search(levels, node.left, depth + 1);
		search(levels, node.right, depth + 1);
	}
}
