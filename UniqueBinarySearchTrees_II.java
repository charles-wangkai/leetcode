import java.util.ArrayList;
import java.util.List;

// Definition for binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
		left = null;
		right = null;
	}
}

public class UniqueBinarySearchTrees_II {
	public List<TreeNode> generateTrees(int n) {
		int[] values = new int[n];
		for (int i = 0; i < values.length; i++) {
			values[i] = i + 1;
		}
		return generateTrees(values, 0, n - 1);
	}

	List<TreeNode> generateTrees(int[] values, int begin, int end) {
		List<TreeNode> roots = new ArrayList<TreeNode>();
		if (begin > end) {
			roots.add(null);
		} else {
			for (int i = begin; i <= end; i++) {
				List<TreeNode> lefts = generateTrees(values, begin, i - 1);
				List<TreeNode> rights = generateTrees(values, i + 1, end);
				for (TreeNode left : lefts) {
					for (TreeNode right : rights) {
						TreeNode root = new TreeNode(values[i]);
						root.left = left;
						root.right = right;
						roots.add(root);
					}
				}
			}
		}
		return roots;
	}
}
