// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class CountCompleteTreeNodes {
	static final int SHIFT_NUM_LIMIT = 30;

	public int countNodes(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int height = findHeight(root);

		int lower = 0;
		int upper = (1 << height) - 1;
		int nodeNum = upper;
		if (findHeight(root, height, upper) == height) {
			nodeNum += upper + 1;
		} else {
			while (lower + 1 != upper) {
				int middle = (lower + upper) / 2;
				if (findHeight(root, height, middle) == height) {
					lower = middle;
				} else {
					upper = middle;
				}
			}
			nodeNum += lower + 1;
		}
		return nodeNum;
	}

	int findHeight(TreeNode root) {
		return findHeight(root, SHIFT_NUM_LIMIT, 0);
	}

	int findHeight(TreeNode root, int shiftNum, int code) {
		int height = -1;
		while (root != null) {
			int half = (1 << shiftNum) >> 1;
			if (code >= half) {
				root = root.right;
				code -= half;
			} else {
				root = root.left;
			}
			shiftNum--;
			height++;
		}
		return height;
	}
}
