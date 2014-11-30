// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

// Definition for binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class ConvertSortedListToBinarySearchTree {
	public TreeNode sortedListToBST(ListNode head) {
		return sortedListToBST(head, getLength(head));
	}

	TreeNode sortedListToBST(ListNode head, int length) {
		if (length == 0) {
			return null;
		}
		int leftLength = length / 2;
		ListNode middle = head;
		for (int i = 0; i < leftLength; i++) {
			middle = middle.next;
		}
		TreeNode root = new TreeNode(middle.val);
		root.left = sortedListToBST(head, leftLength);
		root.right = sortedListToBST(middle.next, length - leftLength - 1);
		return root;
	}

	int getLength(ListNode head) {
		int length = 0;
		for (ListNode p = head; p != null; p = p.next) {
			length++;
		}
		return length;
	}
}
