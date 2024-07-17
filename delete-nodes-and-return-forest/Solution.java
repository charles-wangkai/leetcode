import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
  public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
    List<TreeNode> roots = new ArrayList<>();
    Set<Integer> deletingValues = Arrays.stream(to_delete).boxed().collect(Collectors.toSet());
    search(roots, deletingValues, root, true);

    return roots;
  }

  TreeNode search(
      List<TreeNode> roots, Set<Integer> deletingValues, TreeNode node, boolean isRoot) {
    if (node == null) {
      return null;
    }

    if (deletingValues.contains(node.val)) {
      search(roots, deletingValues, node.left, true);
      search(roots, deletingValues, node.right, true);

      return null;
    }

    if (isRoot) {
      roots.add(node);
    }

    node.left = search(roots, deletingValues, node.left, false);
    node.right = search(roots, deletingValues, node.right, false);

    return node;
  }
}
