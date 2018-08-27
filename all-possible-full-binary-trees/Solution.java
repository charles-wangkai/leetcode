import java.util.ArrayList;
import java.util.Collections;
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
	public List<TreeNode> allPossibleFBT(int N) {
		if (N % 2 == 0) {
			return Collections.emptyList();
		}

		if (N == 1) {
			return Collections.singletonList(new TreeNode(0));
		}

		List<TreeNode> result = new ArrayList<>();
		for (int left = 1; left < N - 1; left += 2) {
			int right = N - 1 - left;

			List<TreeNode> leftSubTrees = allPossibleFBT(left);
			List<TreeNode> rightSubTrees = allPossibleFBT(right);
			for (TreeNode leftSubTree : leftSubTrees) {
				for (TreeNode rightSubTree : rightSubTrees) {
					TreeNode root = new TreeNode(0);
					root.left = leftSubTree;
					root.right = rightSubTree;

					result.add(root);
				}
			}
		}
		return result;
	}
}
