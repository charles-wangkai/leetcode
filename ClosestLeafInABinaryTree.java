import java.util.HashMap;
import java.util.Map;

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class ClosestLeafInABinaryTree {
	int closestLeaf;

	public int findClosestLeaf(TreeNode root, int k) {
		Map<Integer, Leaf> value2leaf = new HashMap<Integer, Leaf>();
		bottomUp(value2leaf, root);
		topDown(value2leaf, root, k, null);
		return closestLeaf;
	}

	Leaf bottomUp(Map<Integer, Leaf> value2leaf, TreeNode node) {
		if (node.left == null && node.right == null) {
			value2leaf.put(node.val, new Leaf(node.val, 0));
			return new Leaf(node.val, 0);
		}

		Leaf result = null;
		if (node.left != null) {
			result = bottomUp(value2leaf, node.left);
		}
		if (node.right != null) {
			Leaf subResult = bottomUp(value2leaf, node.right);
			if (result == null || subResult.distance < result.distance) {
				result = subResult;
			}
		}
		result.distance++;

		value2leaf.put(node.val, new Leaf(result.value, result.distance));
		return result;
	}

	void topDown(Map<Integer, Leaf> value2leaf, TreeNode node, int k, Integer parent) {
		if (parent != null && value2leaf.get(parent).distance + 1 < value2leaf.get(node.val).distance) {
			value2leaf.put(node.val, new Leaf(value2leaf.get(parent).value, value2leaf.get(parent).distance + 1));
		}

		if (node.val == k) {
			closestLeaf = value2leaf.get(node.val).value;
		}

		if (node.left != null) {
			topDown(value2leaf, node.left, k, node.val);
		}
		if (node.right != null) {
			topDown(value2leaf, node.right, k, node.val);
		}
	}
}

class Leaf {
	int value;
	int distance;

	Leaf(int value, int distance) {
		this.value = value;
		this.distance = distance;
	}
}