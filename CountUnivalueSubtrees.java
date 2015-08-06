// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class CountUnivalueSubtrees {
	int uniSubtreeNum;

	public int countUnivalSubtrees(TreeNode root) {
		uniSubtreeNum = 0;
		search(root);
		return uniSubtreeNum;
	}

	Integer search(TreeNode node) {
		if (node == null) {
			return null;
		}

		Integer uniValue = node.val;
		if (node.left != null) {
			Integer leftUniValue = search(node.left);
			if (leftUniValue == null || !leftUniValue.equals(uniValue)) {
				uniValue = null;
			}
		}
		if (node.right != null) {
			Integer rightUniValue = search(node.right);
			if (rightUniValue == null || !rightUniValue.equals(uniValue)) {
				uniValue = null;
			}
		}
		if (uniValue != null) {
			uniSubtreeNum++;
		}
		return uniValue;
	}
}
