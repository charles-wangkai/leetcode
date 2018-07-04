import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		Map<String, TreeNode> subtree2root = new HashMap<String, TreeNode>();
		Set<TreeNode> duplicateRoots = new HashSet<TreeNode>();

		search(subtree2root, duplicateRoots, root);

		return new ArrayList<TreeNode>(duplicateRoots);
	}

	String search(Map<String, TreeNode> subtree2root, Set<TreeNode> duplicateRoots, TreeNode root) {
		if (root == null) {
			return "";
		}

		String result = String.format("%d(%s)(%s)", root.val, search(subtree2root, duplicateRoots, root.left),
				search(subtree2root, duplicateRoots, root.right));

		if (subtree2root.containsKey(result)) {
			duplicateRoots.add(subtree2root.get(result));
		} else {
			subtree2root.put(result, root);
		}

		return result;
	}
}
