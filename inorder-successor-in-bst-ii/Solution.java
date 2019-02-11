// Definition for a Node.
class Node {
	public int val;
	public Node left;
	public Node right;
	public Node parent;
}

public class Solution {
	public Node inorderSuccessor(Node x) {
		if (x == null) {
			return null;
		}

		if (x.right != null) {
			Node result = x.right;
			while (result.left != null) {
				result = result.left;
			}
			return result;
		}

		Node child = x;
		while (child.parent != null && child.parent.right == child) {
			child = child.parent;
		}
		return child.parent;
	}
}
