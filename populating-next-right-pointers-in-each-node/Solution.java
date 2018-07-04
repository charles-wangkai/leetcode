// Definition for binary tree with next pointer.
class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;

	TreeLinkNode(int x) {
		val = x;
	}
}

public class Solution {
	public void connect(TreeLinkNode root) {
		if (root == null) {
			return;
		}
		for (TreeLinkNode head = root; head.left != null; head = head.left) {
			TreeLinkNode prev = null;
			for (TreeLinkNode node = head; node != null; node = node.next) {
				node.left.next = node.right;
				if (prev != null) {
					prev.next = node.left;
				}
				prev = node.right;
			}
		}
	}
}
