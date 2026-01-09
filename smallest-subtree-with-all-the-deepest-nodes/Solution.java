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
  public TreeNode subtreeWithAllDeepest(TreeNode root) {
    Outcome1 outcome1 = search1(root, 0);

    return search2(outcome1.depth(), outcome1.count(), root, 0).subtree();
  }

  Outcome1 search1(TreeNode node, int depth) {
    Outcome1 result = new Outcome1(depth, 1);

    if (node.left != null) {
      result = Outcome1.merge(result, search1(node.left, depth + 1));
    }
    if (node.right != null) {
      result = Outcome1.merge(result, search1(node.right, depth + 1));
    }

    return result;
  }

  Outcome2 search2(int maxDepth, int leafCountWithMaxDepth, TreeNode node, int depth) {
    int targetCount = (depth == maxDepth) ? 1 : 0;
    if (node.left != null) {
      Outcome2 outcome2 = search2(maxDepth, leafCountWithMaxDepth, node.left, depth + 1);
      if (outcome2.subtree() != null) {
        return outcome2;
      }

      targetCount += outcome2.targetCount();
    }
    if (node.right != null) {
      Outcome2 outcome2 = search2(maxDepth, leafCountWithMaxDepth, node.right, depth + 1);
      if (outcome2.subtree() != null) {
        return outcome2;
      }

      targetCount += outcome2.targetCount();
    }

    return new Outcome2((targetCount == leafCountWithMaxDepth) ? node : null, targetCount);
  }
}

record Outcome1(int depth, int count) {
  static Outcome1 merge(Outcome1 o1, Outcome1 o2) {
    if (o1.depth > o2.depth) {
      return o1;
    }
    if (o2.depth > o1.depth) {
      return o2;
    }

    return new Outcome1(o1.depth, o1.count + o2.count);
  }
}

record Outcome2(TreeNode subtree, int targetCount) {}
