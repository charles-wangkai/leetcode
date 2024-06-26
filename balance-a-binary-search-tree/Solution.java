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
  public TreeNode balanceBST(TreeNode root) {
    List<Integer> values = new ArrayList<>();
    search(values, root);

    return buildBalancedBST(values, 0, values.size() - 1);
  }

  void search(List<Integer> values, TreeNode node) {
    if (node != null) {
      search(values, node.left);
      values.add(node.val);
      search(values, node.right);
    }
  }

  TreeNode buildBalancedBST(List<Integer> values, int beginIndex, int endIndex) {
    if (beginIndex > endIndex) {
      return null;
    }

    int middleIndex = (beginIndex + endIndex) / 2;

    return new TreeNode(
        values.get(middleIndex),
        buildBalancedBST(values, beginIndex, middleIndex - 1),
        buildBalancedBST(values, middleIndex + 1, endIndex));
  }
}