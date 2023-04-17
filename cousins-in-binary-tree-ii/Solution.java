import java.util.HashMap;
import java.util.Map;

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
  public TreeNode replaceValueInTree(TreeNode root) {
    Map<Integer, Integer> depthToValueSum = new HashMap<>();
    buildDepthToValueSum(depthToValueSum, root, 0);

    root.val = 0;
    search(depthToValueSum, root, 0);

    return root;
  }

  void search(Map<Integer, Integer> depthToValueSum, TreeNode node, int depth) {
    int childenValue =
        depthToValueSum.getOrDefault(depth + 1, 0)
            - ((node.left == null) ? 0 : node.left.val)
            - ((node.right == null) ? 0 : node.right.val);

    if (node.left != null) {
      node.left.val = childenValue;
      search(depthToValueSum, node.left, depth + 1);
    }
    if (node.right != null) {
      node.right.val = childenValue;
      search(depthToValueSum, node.right, depth + 1);
    }
  }

  void buildDepthToValueSum(Map<Integer, Integer> depthToValueSum, TreeNode node, int depth) {
    depthToValueSum.put(depth, depthToValueSum.getOrDefault(depth, 0) + node.val);

    if (node.left != null) {
      buildDepthToValueSum(depthToValueSum, node.left, depth + 1);
    }
    if (node.right != null) {
      buildDepthToValueSum(depthToValueSum, node.right, depth + 1);
    }
  }
}
