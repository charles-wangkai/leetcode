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
	public boolean leafSimilar(TreeNode root1, TreeNode root2) {
		List<Integer> leafValues1 = new ArrayList<>();
		search(leafValues1, root1);

		List<Integer> leafValues2 = new ArrayList<>();
		search(leafValues2, root2);

		return leafValues1.equals(leafValues2);
	}

	void search(List<Integer> leafValues, TreeNode node) {
		if (node == null) {
			return;
		}

		search(leafValues, node.left);

		if (node.left == null && node.right == null) {
			leafValues.add(node.val);
		}

		search(leafValues, node.right);
	}
}
