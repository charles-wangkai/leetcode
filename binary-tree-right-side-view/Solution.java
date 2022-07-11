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

public class Solution {
  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> rightValues = new ArrayList<>();
    search(rightValues, root, 0);

    return rightValues;
  }

  void search(List<Integer> rightValues, TreeNode node, int depth) {
    if (node == null) {
      return;
    }

    if (depth == rightValues.size()) {
      rightValues.add(node.val);
    } else {
      rightValues.set(depth, node.val);
    }

    search(rightValues, node.left, depth + 1);
    search(rightValues, node.right, depth + 1);
  }
}
