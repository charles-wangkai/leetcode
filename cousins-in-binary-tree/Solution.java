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
  public boolean isCousins(TreeNode root, int x, int y) {
    Location locationX = search(root, null, x, 0);
    Location locationY = search(root, null, y, 0);

    return locationX.depth == locationY.depth && locationX.parent != locationY.parent;
  }

  Location search(TreeNode node, TreeNode parent, int target, int depth) {
    if (node == null) {
      return null;
    }

    if (node.val == target) {
      return new Location(depth, parent);
    }

    Location leftSubResult = search(node.left, node, target, depth + 1);
    if (leftSubResult != null) {
      return leftSubResult;
    }

    return search(node.right, node, target, depth + 1);
  }
}

class Location {
  int depth;
  TreeNode parent;

  Location(int depth, TreeNode parent) {
    this.depth = depth;
    this.parent = parent;
  }
}
