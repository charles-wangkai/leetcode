import java.util.HashSet;
import java.util.Set;

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
  public int heightOfTree(TreeNode root) {
    Set<Integer> leafValues = findLeafValues(root);

    return computeHeight(leafValues, root, 0);
  }

  int computeHeight(Set<Integer> leafValues, TreeNode node, int height) {
    if (node == null) {
      return 0;
    }
    if (leafValues.contains(node.val)) {
      return height;
    }

    return Math.max(
        computeHeight(leafValues, node.left, height + 1),
        computeHeight(leafValues, node.right, height + 1));
  }

  Set<Integer> findLeafValues(TreeNode root) {
    Set<Integer> seen = new HashSet<>();
    TreeNode node = root;
    while (!seen.contains(node.val)) {
      seen.add(node.val);

      if (node.left != null) {
        node = node.left;
      } else {
        node = node.right;
      }
    }

    Set<Integer> result = new HashSet<>();
    result.add(node.val);
    for (TreeNode p = node.right; p != node; p = p.right) {
      result.add(p.val);
    }

    return result;
  }
}
