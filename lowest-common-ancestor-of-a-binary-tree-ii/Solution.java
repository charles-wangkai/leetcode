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
    while (index + 1 < pPath.size()
        && index + 1 < qPath.size()
        && pPath.get(index + 1) == qPath.get(index + 1)) {
      ++index;
    }

    return pPath.get(index);
  }

  List<TreeNode> findPath(TreeNode target, List<TreeNode> path, TreeNode current) {
    if (current == null) {
      return null;
    }

    path.add(current);

    if (current == target) {
      return path;
    }

    List<TreeNode> leftSubResult = findPath(target, path, current.left);
    if (leftSubResult != null) {
      return leftSubResult;
    }

    List<TreeNode> rightSubResult = findPath(target, path, current.right);
    if (rightSubResult != null) {
      return rightSubResult;
    }

    path.remove(path.size() - 1);

    return null;
  }
}
