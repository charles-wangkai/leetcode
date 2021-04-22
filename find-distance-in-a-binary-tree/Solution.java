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
  public int findDistance(TreeNode root, int p, int q) {
    List<TreeNode> pPath = findPath(root, p);
    List<TreeNode> qPath = findPath(root, q);

    int beginIndex = 0;
    while (beginIndex + 1 != pPath.size()
        && beginIndex + 1 != qPath.size()
        && pPath.get(beginIndex + 1) == qPath.get(beginIndex + 1)) {
      ++beginIndex;
    }

    return (pPath.size() - 1 - beginIndex) + (qPath.size() - 1 - beginIndex);
  }

  List<TreeNode> findPath(TreeNode root, int target) {
    List<TreeNode> path = new ArrayList<>();
    search(target, path, root);

    return path;
  }

  boolean search(int target, List<TreeNode> path, TreeNode node) {
    if (node == null) {
      return false;
    }

    path.add(node);
    if (node.val == target || search(target, path, node.left) || search(target, path, node.right)) {
      return true;
    }
    path.remove(path.size() - 1);

    return false;
  }
}
