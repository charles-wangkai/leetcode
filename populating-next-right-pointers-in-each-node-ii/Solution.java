// Definition for a Node.
class Node {
  public int val;
  public Node left;
  public Node right;
  public Node next;

  public Node() {}

  public Node(int _val) {
    val = _val;
  }

  public Node(int _val, Node _left, Node _right, Node _next) {
    val = _val;
    left = _left;
    right = _right;
    next = _next;
  }
}

class Solution {
  public Node connect(Node root) {
    Node nextHead;
    for (Node head = root; head != null; head = nextHead) {
      nextHead = null;
      Node prev = null;
      for (Node node = head; node != null; node = node.next) {
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

    return root;
  }
}
