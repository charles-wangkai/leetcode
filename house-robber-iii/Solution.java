import java.util.HashMap;
import java.util.Map;

//Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Solution {
	Map<TreeNode, Integer> node2chosenMaxMoney = new HashMap<TreeNode, Integer>();
	Map<TreeNode, Integer> node2notChosenMaxMoney = new HashMap<TreeNode, Integer>();

	public int rob(TreeNode root) {
		if (!node2chosenMaxMoney.containsKey(root)) {
			search(root);
		}

		return Math.max(node2chosenMaxMoney.get(root), node2notChosenMaxMoney.get(root));
	}

	void search(TreeNode node) {
		if (node == null) {
			node2chosenMaxMoney.put(node, 0);
			node2notChosenMaxMoney.put(node, 0);
		} else {
			node2chosenMaxMoney.put(node,
					node.val + (node.left == null ? 0 : rob(node.left.left) + rob(node.left.right))
							+ (node.right == null ? 0 : rob(node.right.left) + rob(node.right.right)));
			node2notChosenMaxMoney.put(node, rob(node.left) + rob(node.right));
		}
	}
}
