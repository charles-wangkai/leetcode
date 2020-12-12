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
  int maxDepth;
  int leafNumWithMaxDepth;

  TreeNode subtree;

  public TreeNode subtreeWithAllDeepest(TreeNode root) {
    maxDepth = -1;
    leafNumWithMaxDepth = -1;
    searchMaxDepth(root, 0);

    subtree = null;
    searchSubtree(root, 0);
    return subtree;
  }

  void searchMaxDepth(TreeNode node, int depth) {
    if (node == null) {
      return;
    }

    if (depth > maxDepth) {
      maxDepth = depth;
      leafNumWithMaxDepth = 1;
    } else if (depth == maxDepth) {
      ++leafNumWithMaxDepth;
    }

    searchMaxDepth(node.left, depth + 1);
    searchMaxDepth(node.right, depth + 1);
  }

  int searchSubtree(TreeNode node, int depth) {
    int targetNum;
    if (node.left == null) {
      if (node.right == null) {
        if (depth == maxDepth) {
          targetNum = 1;
        } else {
          targetNum = 0;
        }
      } else {
        targetNum = searchSubtree(node.right, depth + 1);
      }
    } else {
      if (node.right == null) {
        targetNum = searchSubtree(node.left, depth + 1);
      } else {
        targetNum = searchSubtree(node.left, depth + 1) + searchSubtree(node.right, depth + 1);
      }
    }

    if (subtree == null && targetNum == leafNumWithMaxDepth) {
      subtree = node;
    }

    return targetNum;
  }
}
