// Definition for singly-linked list.
class ListNode {
  int val;
  ListNode next;

  ListNode() {}

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

// Definition for a binary tree node.
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {}

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

class Solution {
  public TreeNode sortedListToBST(ListNode head) {
    return sortedListToBST(head, computeLength(head));
  }

  TreeNode sortedListToBST(ListNode head, int length) {
    if (length == 0) {
      return null;
    }

    int leftLength = length / 2;
    ListNode middle = head;
    for (int i = 0; i < leftLength; ++i) {
      middle = middle.next;
    }

    return new TreeNode(
        middle.val,
        sortedListToBST(head, leftLength),
        sortedListToBST(middle.next, length - leftLength - 1));
  }

  int computeLength(ListNode head) {
    int length = 0;
    for (ListNode p = head; p != null; p = p.next) {
      ++length;
    }

    return length;
  }
}
