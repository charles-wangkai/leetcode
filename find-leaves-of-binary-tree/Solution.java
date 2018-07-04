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
	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		while (root != null) {
			List<Integer> leaves = new ArrayList<Integer>();
			if (removeLeaves(leaves, root)) {
				root = null;
			}

			result.add(leaves);
		}
		return result;
	}

	private boolean removeLeaves(List<Integer> leaves, TreeNode node) {
		boolean leaf = isLeaf(node);
		if (leaf) {
			leaves.add(node.val);
		}

		if (node.left != null) {
			if (removeLeaves(leaves, node.left)) {
				node.left = null;
			}
		}

		if (node.right != null) {
			if (removeLeaves(leaves, node.right)) {
				node.right = null;
			}
		}

		return leaf;
	}

	private boolean isLeaf(TreeNode node) {
		return node.left == null && node.right == null;
	}
}
