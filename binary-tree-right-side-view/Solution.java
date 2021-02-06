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
    List<Integer> result = new ArrayList<>();
    search(result, root, 0);

    return result;
  }

  void search(List<Integer> result, TreeNode node, int depth) {
    if (node == null) {
      return;
    }

    if (depth == result.size()) {
      result.add(node.val);
    } else {
      result.set(depth, node.val);
    }

    search(result, node.left, depth + 1);
    search(result, node.right, depth + 1);
  }
}
