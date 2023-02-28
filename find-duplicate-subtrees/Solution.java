import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
  public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
    List<TreeNode> duplicateRoots = new ArrayList<>();
    Map<String, Integer> subtreeToCount = new HashMap<>();
    search(duplicateRoots, subtreeToCount, root);

    return duplicateRoots;
  }

  String search(List<TreeNode> duplicateRoots, Map<String, Integer> subtreeToCount, TreeNode node) {
    if (node == null) {
      return "";
    }

    String subtree =
        String.format(
            "%d(%s)(%s)",
            node.val,
            search(duplicateRoots, subtreeToCount, node.left),
            search(duplicateRoots, subtreeToCount, node.right));
    subtreeToCount.put(subtree, subtreeToCount.getOrDefault(subtree, 0) + 1);
    if (subtreeToCount.get(subtree) == 2) {
      duplicateRoots.add(node);
    }

    return subtree;
  }
}
