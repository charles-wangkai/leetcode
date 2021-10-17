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
  public int pathSum(TreeNode root, int targetSum) {
    Map<Integer, Integer> sumToCount = new HashMap<>();
    sumToCount.put(0, 1);

    return search(targetSum, sumToCount, 0, root);
  }

  int search(int targetSum, Map<Integer, Integer> sumToCount, int prev, TreeNode node) {
    if (node == null) {
      return 0;
    }

    int current = prev + node.val;
    int result = sumToCount.getOrDefault(current - targetSum, 0);

    sumToCount.put(current, sumToCount.getOrDefault(current, 0) + 1);
    result +=
        search(targetSum, sumToCount, current, node.left)
            + search(targetSum, sumToCount, current, node.right);
    sumToCount.put(current, sumToCount.get(current) - 1);

    return result;
  }
}
