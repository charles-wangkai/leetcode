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
  TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    List<TreeNode> pPath = new ArrayList<>();
    buildPath(pPath, p, root);

    List<TreeNode> qPath = new ArrayList<>();
    buildPath(qPath, q, root);

    int index = 0;
    while (index + 1 != pPath.size()
        && index + 1 != qPath.size()
        && pPath.get(index + 1) == qPath.get(index + 1)) {
      ++index;
    }

    return pPath.get(index);
  }

  boolean buildPath(List<TreeNode> path, TreeNode target, TreeNode node) {
    path.add(node);
    if (node == target) {
      return true;
    }

    if (node.left != null && buildPath(path, target, node.left)) {
      return true;
    }
    if (node.right != null && buildPath(path, target, node.right)) {
      return true;
    }

    path.remove(path.size() - 1);

    return false;
  }
}
