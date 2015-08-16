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

public class BinaryTreePaths {
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> paths = new ArrayList<String>();
		search(paths, root, new ArrayList<String>());
		return paths;
	}

	void search(List<String> paths, TreeNode node, List<String> path) {
		if (node == null) {
			return;
		}

		path.add(node.val + "");

		if (node.left == null && node.right == null) {
			paths.add(String.join("->", path));
		} else {
			if (node.left != null) {
				search(paths, node.left, path);
			}
			if (node.right != null) {
				search(paths, node.right, path);
			}
		}

		path.remove(path.size() - 1);
	}
}
