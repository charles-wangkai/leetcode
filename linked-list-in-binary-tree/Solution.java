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
  public boolean isSubPath(ListNode head, TreeNode root) {
    return root != null
        && (check(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right));
  }

  boolean check(ListNode head, TreeNode root) {
    return head == null
        || (root != null
            && head.val == root.val
            && (check(head.next, root.left) || check(head.next, root.right)));
  }
}