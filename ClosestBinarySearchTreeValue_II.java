import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class ClosestBinarySearchTreeValue_II {
	@SuppressWarnings("unchecked")
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		Stack<DirectionAndNode> leftPath = new Stack<DirectionAndNode>();
		Stack<DirectionAndNode> rightPath = new Stack<DirectionAndNode>();

		Stack<DirectionAndNode> path = new Stack<DirectionAndNode>();
		boolean leftOrRight = false;
		TreeNode node = root;
		while (node != null) {
			path.push(new DirectionAndNode(leftOrRight, node));

			if (node.val >= target) {
				rightPath = (Stack<DirectionAndNode>) path.clone();
				node = node.left;
				leftOrRight = true;
			} else {
				leftPath = (Stack<DirectionAndNode>) path.clone();
				node = node.right;
				leftOrRight = false;
			}
		}

		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < k; i++) {
			Integer leftValue = getValue(leftPath);
			Integer rightValue = getValue(rightPath);
			if (rightValue == null
					|| (leftValue != null && Math.abs(leftValue - target) < Math
							.abs(rightValue - target))) {
				result.add(leftValue);
				moveToPredecessor(leftPath);
			} else {
				result.add(rightValue);
				moveToSuccessor(rightPath);
			}
		}
		return result;
	}

	Integer getValue(Stack<DirectionAndNode> path) {
		return path.empty() ? null : path.peek().node.val;
	}

	void moveToPredecessor(Stack<DirectionAndNode> path) {
		if (path.empty()) {
			return;
		}

		if (path.peek().node.left != null) {
			path.push(new DirectionAndNode(true, path.peek().node.left));
			while (path.peek().node.right != null) {
				path.push(new DirectionAndNode(false, path.peek().node.right));
			}
		} else {
			while (!path.empty() && path.peek().leftOrRight) {
				path.pop();
			}
			if (!path.empty()) {
				path.pop();
			}
		}
	}

	void moveToSuccessor(Stack<DirectionAndNode> path) {
		if (path.empty()) {
			return;
		}

		if (path.peek().node.right != null) {
			path.push(new DirectionAndNode(false, path.peek().node.right));
			while (path.peek().node.left != null) {
				path.push(new DirectionAndNode(true, path.peek().node.left));
			}
		} else {
			while (!path.empty() && !path.peek().leftOrRight) {
				path.pop();
			}
			if (!path.empty()) {
				path.pop();
			}
		}
	}
}

class DirectionAndNode {
	boolean leftOrRight;
	TreeNode node;

	DirectionAndNode(boolean leftOrRight, TreeNode node) {
		this.leftOrRight = leftOrRight;
		this.node = node;
	}
}