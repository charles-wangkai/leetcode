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
  public List<Integer> largestValues(TreeNode root) {
    List<Integer> maxValues = new ArrayList<>();
    search(maxValues, root, 0);

    return maxValues;
  }

  void search(List<Integer> maxValues, TreeNode node, int depth) {
    if (node != null) {
      if (depth == maxValues.size()) {
        maxValues.add(node.val);
      } else {
        maxValues.set(depth, Math.max(maxValues.get(depth), node.val));
      }

      search(maxValues, node.left, depth + 1);
      search(maxValues, node.right, depth + 1);
    }
  }
}
