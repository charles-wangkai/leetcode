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
  public String getDirections(TreeNode root, int startValue, int destValue) {
    String startPath = findPath(root, startValue, new StringBuilder());
    String destPath = findPath(root, destValue, new StringBuilder());

    int lcaIndex = 0;
    while (lcaIndex != startPath.length()
        && lcaIndex != destPath.length()
        && startPath.charAt(lcaIndex) == destPath.charAt(lcaIndex)) {
      ++lcaIndex;
    }

    return String.format(
        "%s%s", "U".repeat(startPath.length() - lcaIndex), destPath.substring(lcaIndex));
  }

  String findPath(TreeNode node, int targetValue, StringBuilder path) {
    if (node == null) {
      return null;
    }
    if (node.val == targetValue) {
      return path.toString();
    }

    path.append('L');
    String leftSubResult = findPath(node.left, targetValue, path);
    if (leftSubResult != null) {
      return leftSubResult;
    }
    path.deleteCharAt(path.length() - 1);

    path.append('R');
    String rightSubResult = findPath(node.right, targetValue, path);
    if (rightSubResult != null) {
      return rightSubResult;
    }
    path.deleteCharAt(path.length() - 1);

    return null;
  }
}