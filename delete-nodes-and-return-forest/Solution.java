import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
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

public class Solution {
	public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
		List<TreeNode> roots = new ArrayList<>();
		Set<Integer> toDeleteSet = Arrays.stream(to_delete).boxed().collect(Collectors.toSet());
		search(roots, toDeleteSet, root, true);

		return roots;
	}

	TreeNode search(List<TreeNode> roots, Set<Integer> toDeleteSet, TreeNode node, boolean isRoot) {
		if (node == null) {
			return null;
		}

		if (toDeleteSet.contains(node.val)) {
			search(roots, toDeleteSet, node.left, true);
			search(roots, toDeleteSet, node.right, true);

			return null;
		} else {
			if (isRoot) {
				roots.add(node);
			}

			node.left = search(roots, toDeleteSet, node.left, false);
			node.right = search(roots, toDeleteSet, node.right, false);

			return node;
		}
	}
}
