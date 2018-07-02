import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class AllNodesDistanceKInBinaryTree {
	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
		if (K == 0) {
			return Collections.singletonList(target.val);
		}

		List<TreeNode> path = findPath(root, target);

		List<Integer> result = searchChildren(target, K);
		if (K < path.size()) {
			result.add(path.get(K).val);
		}
		for (int i = 1; i < path.size() && i < K; i++) {
			TreeNode start;
			if (path.get(i).left == path.get(i - 1)) {
				start = path.get(i).right;
			} else {
				start = path.get(i).left;
			}

			result.addAll(searchChildren(start, K - (i + 1)));
		}
		return result;
	}

	List<Integer> searchChildren(TreeNode node, int distance) {
		if (node == null) {
			return new ArrayList<>();
		}
		if (distance == 0) {
			List<Integer> result = new ArrayList<>();
			result.add(node.val);

			return result;
		}

		List<Integer> result = searchChildren(node.left, distance - 1);
		result.addAll(searchChildren(node.right, distance - 1));
		return result;
	}

	List<TreeNode> findPath(TreeNode node, TreeNode target) {
		if (node == null) {
			return null;
		}

		if (node == target) {
			List<TreeNode> result = new ArrayList<>();
			result.add(node);

			return result;
		}

		List<TreeNode> path = findPath(node.left, target);
		if (path == null) {
			path = findPath(node.right, target);
		}
		if (path != null) {
			path.add(node);
		}
		return path;
	}
}
