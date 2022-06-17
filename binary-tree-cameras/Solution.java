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
  Map<TreeNode, Integer> minCameraCoverCache = new HashMap<>();
  Map<TreeNode, Integer> searchCameraNodeCache = new HashMap<>();

  public int minCameraCover(TreeNode root) {
    if (root == null) {
      return 0;
    }

    if (!minCameraCoverCache.containsKey(root)) {
      int result = searchCameraNode(root);
      if (root.left != null) {
        result = Math.min(result, searchCameraNode(root.left) + minCameraCover(root.right));
      }
      if (root.right != null) {
        result = Math.min(result, minCameraCover(root.left) + searchCameraNode(root.right));
      }

      minCameraCoverCache.put(root, result);
    }

    return minCameraCoverCache.get(root);
  }

  int searchCameraNode(TreeNode node) {
    if (!searchCameraNodeCache.containsKey(node)) {
      searchCameraNodeCache.put(
          node,
          1
              + ((node.left == null)
                  ? 0
                  : Math.min(
                      searchCameraNode(node.left),
                      minCameraCover(node.left.left) + minCameraCover(node.left.right)))
              + ((node.right == null)
                  ? 0
                  : Math.min(
                      searchCameraNode(node.right),
                      minCameraCover(node.right.left) + minCameraCover(node.right.right))));
    }

    return searchCameraNodeCache.get(node);
  }
}
