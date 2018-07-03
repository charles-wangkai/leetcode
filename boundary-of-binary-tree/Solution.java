import java.util.ArrayList;
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

public class Solution {
	boolean hasLeftSubtree;
	List<TreeNode> lastLeafPath;

	public List<Integer> boundaryOfBinaryTree(TreeNode root) {
		List<Integer> boundary = new ArrayList<Integer>();
		if (root != null) {
			hasLeftSubtree = root.left != null;
			boolean hasRightSubtree = root.right != null;

			if (!hasLeftSubtree) {
				boundary.add(root.val);
			}

			if (hasLeftSubtree || hasRightSubtree) {
				lastLeafPath = null;

				search(boundary, root, new ArrayList<TreeNode>());

				if (root.right != null) {
					for (int i = lastLeafPath.size() - 2; i > 0; i--) {
						boundary.add(lastLeafPath.get(i).val);
					}
				}
			}
		}
		return boundary;
	}

	void search(List<Integer> boundary, TreeNode node, List<TreeNode> path) {
		path.add(node);

		if (node.left == null) {
			if (node.right == null) {
				if (hasLeftSubtree && lastLeafPath == null) {
					boundary.addAll(path.stream().map(n -> n.val).collect(Collectors.toList()));
				} else {
					boundary.add(node.val);
				}

				lastLeafPath = new ArrayList<TreeNode>(path);
			}
		} else {
			search(boundary, node.left, path);
		}

		if (node.right != null) {
			search(boundary, node.right, path);
		}

		path.remove(path.size() - 1);
	}
}
