import java.util.ArrayList;
import java.util.List;

// Definition for a binary tree node.
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    List<TreeNode> pPath = findPath(p, new ArrayList<>(), root);
    List<TreeNode> qPath = findPath(q, new ArrayList<>(), root);
    if (pPath == null || qPath == null) {
      return null;
    }

    int index = 0;
    while (index != pPath.size() - 1
        && index != qPath.size() - 1
        && pPath.get(index + 1) == qPath.get(index + 1)) {
      ++index;
    }

    return pPath.get(index);
  }

  List<TreeNode> findPath(TreeNode target, List<TreeNode> path, TreeNode node) {
    if (node == null) {
      return null;
    }

    path.add(node);

    if (node == target) {
      return path;
    }

    List<TreeNode> leftSubResult = findPath(target, path, node.left);
    if (leftSubResult != null) {
      return leftSubResult;
    }

    List<TreeNode> rightSubResult = findPath(target, path, node.right);
    if (rightSubResult != null) {
      return rightSubResult;
    }

    path.removeLast();

    return null;
  }
}
