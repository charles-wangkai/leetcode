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

public class BinaryTreeRightSideView {
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		search(result, root, 0);
		return result;
	}

	void search(List<Integer> result, TreeNode node, int depth) {
		if (node == null) {
			return;
		}

		if (depth == result.size()) {
			result.add(node.val);
		} else {
			result.set(depth, node.val);
		}

		search(result, node.left, depth + 1);
		search(result, node.right, depth + 1);
	}
}
