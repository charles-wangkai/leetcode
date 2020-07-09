import java.util.ArrayList;
import java.util.List;

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

class Solution {
	public int widthOfBinaryTree(TreeNode root) {
		List<List<Integer>> positionLists = new ArrayList<>();

		search(positionLists, root, 0, 0);

		return positionLists.stream()
				.mapToInt(positionList -> positionList.get(positionList.size() - 1) - positionList.get(0) + 1).max()
				.getAsInt();
	}

	void search(List<List<Integer>> positionLists, TreeNode node, int depth, int position) {
		if (node == null) {
			return;
		}

		if (positionLists.size() == depth) {
			positionLists.add(new ArrayList<>());
		}
		positionLists.get(depth).add(position);

		search(positionLists, node.left, depth + 1, position * 2);
		search(positionLists, node.right, depth + 1, position * 2 + 1);
	}
}
