import java.util.ArrayList;
import java.util.List;

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
  public boolean isEvenOddTree(TreeNode root) {
    return search(new ArrayList<>(), root, 0);
  }

  boolean search(List<Integer> lasts, TreeNode node, int depth) {
    if (node == null) {
      return true;
    }
    if (node.val % 2 == depth % 2) {
      return false;
    }

    if (depth == lasts.size()) {
      lasts.add(node.val);
    } else if ((depth % 2 == 0 && node.val <= lasts.get(depth))
        || (depth % 2 != 0 && node.val >= lasts.get(depth))) {
      return false;
    } else {
      lasts.set(depth, node.val);
    }

    return search(lasts, node.left, depth + 1) && search(lasts, node.right, depth + 1);
  }
}
