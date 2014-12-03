// Definition for binary tree with next pointer.
class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;

	TreeLinkNode(int x) {
		val = x;
	}
}

public class PopulatingNextRightPointersInEachNode_II {
	public void connect(TreeLinkNode root) {
		TreeLinkNode nextHead;
		for (TreeLinkNode head = root; head != null; head = nextHead) {
			nextHead = null;
			TreeLinkNode prev = null;
			for (TreeLinkNode node = head; node != null; node = node.next) {
				if (node.left != null) {
					if (nextHead == null) {
						nextHead = node.left;
					}
					if (prev != null) {
						prev.next = node.left;
					}
					prev = node.left;
				}
				if (node.right != null) {
					if (nextHead == null) {
						nextHead = node.right;
					}
					if (prev != null) {
						prev.next = node.right;
					}
					prev = node.right;
				}
			}
		}
	}
}
