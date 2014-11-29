import java.util.ArrayList;
import java.util.List;

// Definition for binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class BinaryTreeZigzagLevelOrderTraversal {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> levels = new ArrayList<List<Integer>>();
		search(levels, root, 0);
		for (int i = 1; i < levels.size(); i += 2) {
			reverse(levels.get(i));
		}
		return levels;
	}

	void search(List<List<Integer>> levels, TreeNode node, int depth) {
		if (node == null) {
			return;
		}
		if (depth == levels.size()) {
			levels.add(new ArrayList<Integer>());
		}
		levels.get(depth).add(node.val);
		search(levels, node.left, depth + 1);
		search(levels, node.right, depth + 1);
	}

	void reverse(List<Integer> level) {
		for (int i = 0, j = level.size() - 1; i < j; i++, j--) {
			int temp = level.get(i);
			level.set(i, level.get(j));
			level.set(j, temp);
		}
	}
}
