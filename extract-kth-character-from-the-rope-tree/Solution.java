// Definition for a rope tree node.
class RopeTreeNode {
  int len;
  String val;
  RopeTreeNode left;
  RopeTreeNode right;

  RopeTreeNode() {}

  RopeTreeNode(String val) {
    this.len = 0;
    this.val = val;
  }

  RopeTreeNode(int len) {
    this.len = len;
    this.val = "";
  }

  RopeTreeNode(int len, RopeTreeNode left, RopeTreeNode right) {
    this.len = len;
    this.val = "";
    this.left = left;
    this.right = right;
  }
}

class Solution {
  public char getKthCharacter(RopeTreeNode root, int k) {
    if (!root.val.isEmpty()) {
      return root.val.charAt(k - 1);
    }

    int leftLength =
        (root.left == null) ? 0 : ((root.left.len == 0) ? root.left.val.length() : root.left.len);

    return (leftLength >= k)
        ? getKthCharacter(root.left, k)
        : getKthCharacter(root.right, k - leftLength);
  }
}
