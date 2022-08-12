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
    List<TreeNode> pPath = new ArrayList<>();
    findPath(pPath, root, p);

    List<TreeNode> qPath = new ArrayList<>();
    findPath(qPath, root, q);

    int index = 0;
    while (index + 1 != pPath.size()
        && index + 1 != qPath.size()
        && pPath.get(index + 1) == qPath.get(index + 1)) {
      ++index;
    }

    return pPath.get(index);
  }

  void findPath(List<TreeNode> path, TreeNode node, TreeNode target) {
    path.add(node);

    if (node == target) {
      return;
    }

    if (target.val < node.val) {
      findPath(path, node.left, target);
    } else {
      findPath(path, node.right, target);
    }
  }
}
