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
	public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
		List<Integer> values1 = new ArrayList<>();
		search(values1, root1);

		List<Integer> values2 = new ArrayList<>();
		search(values2, root2);

		return merge(values1, values2);
	}

	void search(List<Integer> values, TreeNode node) {
		if (node == null) {
			return;
		}

		search(values, node.left);
		values.add(node.val);
		search(values, node.right);
	}

	List<Integer> merge(List<Integer> values1, List<Integer> values2) {
		List<Integer> result = new ArrayList<>();
		int index1 = 0;
		int index2 = 0;
		while (index1 != values1.size() || index2 != values2.size()) {
			if (index2 == values2.size() || (index1 != values1.size() && values1.get(index1) <= values2.get(index2))) {
				result.add(values1.get(index1));
				index1++;
			} else {
				result.add(values2.get(index2));
				index2++;
			}
		}

		return result;
	}
}
