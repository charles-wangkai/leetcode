// Definition for a binary tree node.
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

public class Solution {
  String minPath;

  public String smallestFromLeaf(TreeNode root) {
    minPath = null;
    search(root, "");
    return minPath;
  }

  void search(TreeNode node, String path) {
    path = (char) (node.val + 'a') + path;

    if (node.left == null
        && node.right == null
        && (minPath == null || path.compareTo(minPath) < 0)) {
      minPath = path;
    }

    if (node.left != null) {
      search(node.left, path);
    }
    if (node.right != null) {
      search(node.right, path);
    }
  }
}
