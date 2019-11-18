import java.util.HashSet;
import java.util.Set;

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class FindElements {
	Set<Integer> values = new HashSet<>();

	public FindElements(TreeNode root) {
		recover(root, 0);
	}

	void recover(TreeNode node, int value) {
		if (node == null) {
			return;
		}

		node.val = value;
		values.add(value);

		recover(node.left, 2 * value + 1);
		recover(node.right, 2 * value + 2);
	}

	public boolean find(int target) {
		return values.contains(target);
	}
}

// Your FindElements object will be instantiated and called as such:
// FindElements obj = new FindElements(root);
// boolean param_1 = obj.find(target);
