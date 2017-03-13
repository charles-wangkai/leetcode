// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class ConstructBinaryTreeFromString {
	public TreeNode str2tree(String s) {
		if (s.isEmpty()) {
			return null;
		}

		int leftBeginIndex = 0;
		while (leftBeginIndex < s.length() && s.charAt(leftBeginIndex) != '(') {
			leftBeginIndex++;
		}

		int value = Integer.parseInt(s.substring(0, leftBeginIndex));

		TreeNode node = new TreeNode(value);

		if (leftBeginIndex < s.length()) {
			int depth = 1;
			int rightBeginIndex = leftBeginIndex + 1;
			while (depth != 0) {
				char ch = s.charAt(rightBeginIndex);
				if (ch == '(') {
					depth++;
				} else if (ch == ')') {
					depth--;
				}

				rightBeginIndex++;
			}

			node.left = str2tree(s.substring(leftBeginIndex + 1, rightBeginIndex - 1));

			if (rightBeginIndex < s.length()) {
				node.right = str2tree(s.substring(rightBeginIndex + 1, s.length() - 1));
			}
		}

		return node;
	}
}
