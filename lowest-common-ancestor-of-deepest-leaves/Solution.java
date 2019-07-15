import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	Map<TreeNode, List<TreeNode>> nodeToPath;
	int maxDepth;
	List<TreeNode> leavesWithMaxDepth;

	public TreeNode lcaDeepestLeaves(TreeNode root) {
		nodeToPath = new HashMap<>();
		maxDepth = -1;
		leavesWithMaxDepth = new ArrayList<>();

		search(root, new ArrayList<>());

		List<TreeNode> nodes = leavesWithMaxDepth;
		while (nodes.size() > 1) {
			TreeNode node1 = nodes.remove(nodes.size() - 1);
			TreeNode node2 = nodes.remove(nodes.size() - 1);

			nodes.add(findLca(node1, node2));
		}
		return nodes.get(0);
	}

	void search(TreeNode node, List<TreeNode> path) {
		if (node.left == null && node.right == null) {
			int depth = path.size();
			if (depth == maxDepth) {
				leavesWithMaxDepth.add(node);
			} else if (depth > maxDepth) {
				maxDepth = depth;

				leavesWithMaxDepth.clear();
				leavesWithMaxDepth.add(node);
			}
		}

		path.add(node);

		nodeToPath.put(node, new ArrayList<>(path));

		if (node.left != null) {
			search(node.left, path);
		}
		if (node.right != null) {
			search(node.right, path);
		}

		path.remove(path.size() - 1);
	}

	TreeNode findLca(TreeNode node1, TreeNode node2) {
		List<TreeNode> path1 = nodeToPath.get(node1);
		List<TreeNode> path2 = nodeToPath.get(node2);

		for (int i = 0;; i++) {
			if (i == path1.size() || i == path2.size() || path1.get(i) != path2.get(i)) {
				return path1.get(i - 1);
			}
		}
	}
}
