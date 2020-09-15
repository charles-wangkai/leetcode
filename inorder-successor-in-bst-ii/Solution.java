// Definition for a Node.
class Node {
	public int val;
	public Node left;
	public Node right;
	public Node parent;
}

class Solution {
	public Node inorderSuccessor(Node node) {
		if (node == null) {
			return null;
		}

		if (node.right != null) {
			Node result = node.right;
			while (result.left != null) {
				result = result.left;
			}

			return result;
		}

		Node child = node;
		while (child.parent != null && child.parent.right == child) {
			child = child.parent;
		}

		return child.parent;
	}
}
