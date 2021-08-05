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
  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    if (root == null) {
      return List.of();
    }

    List<List<Integer>> paths = new ArrayList<>();
    search(paths, new ArrayList<>(), root, targetSum);

    return paths;
  }

  void search(List<List<Integer>> paths, List<Integer> path, TreeNode node, int restSum) {
    path.add(node.val);

    if (node.left == null && node.right == null && node.val == restSum) {
      paths.add(List.copyOf(path));
    } else {
      if (node.left != null) {
        search(paths, path, node.left, restSum - node.val);
      }
      if (node.right != null) {
        search(paths, path, node.right, restSum - node.val);
      }
    }

    path.remove(path.size() - 1);
  }
}
