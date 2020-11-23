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
  public int rob(TreeNode root) {
    Element subResult = search(root);

    return Math.max(subResult.chosenSum, subResult.skipSum);
  }

  Element search(TreeNode node) {
    if (node == null) {
      return new Element(0, 0);
    }

    Element leftSubResult = search(node.left);
    Element rightSubResult = search(node.right);
    int chosenSum = node.val + leftSubResult.skipSum + rightSubResult.skipSum;
    int skipSum =
        Math.max(leftSubResult.chosenSum, leftSubResult.skipSum)
            + Math.max(rightSubResult.chosenSum, rightSubResult.skipSum);

    return new Element(chosenSum, skipSum);
  }
}

class Element {
  int chosenSum;
  int skipSum;

  Element(int chosenSum, int skipSum) {
    this.chosenSum = chosenSum;
    this.skipSum = skipSum;
  }
}
