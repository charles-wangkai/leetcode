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

public class LowestCommonAncestorOfABinarySearchTree {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		List<TreeNode> pathP = new ArrayList<TreeNode>();
		findPath(root, p, pathP);
		System.out.println(pathP);

		List<TreeNode> pathQ = new ArrayList<TreeNode>();
		findPath(root, q, pathQ);
		System.out.println(pathQ);

		TreeNode result = null;
		int index = 0;
		while (index < pathP.size() && index < pathQ.size()
				&& pathP.get(index) == pathQ.get(index)) {
			result = pathP.get(index);
			index++;
		}
		return result;
	}

	boolean findPath(TreeNode root, TreeNode node, List<TreeNode> path) {
		path.add(root);
		if (root == node) {
			return true;
		}

		if (root != null) {
			if (root.val >= node.val && findPath(root.left, node, path)) {
				return true;
			}
			if (root.val <= node.val && findPath(root.right, node, path)) {
				return true;
			}
		}
		path.remove(path.size() - 1);
		return false;
	}
}
