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
  public TreeNode correctBinaryTree(TreeNode root) {
    Map<TreeNode, TreeNode> nodeToParent = new HashMap<>();
    TreeNode invalidNode = findInvalidNode(nodeToParent, null, root);

    return remove(invalidNode, root);
  }

  TreeNode findInvalidNode(Map<TreeNode, TreeNode> nodeToParent, TreeNode parent, TreeNode node) {
    if (node == null) {
      return null;
    }
    if (nodeToParent.containsKey(node)) {
      return nodeToParent.get(node);
    }

    nodeToParent.put(node, parent);

    TreeNode leftSubResult = findInvalidNode(nodeToParent, node, node.left);
    if (leftSubResult != null) {
      return leftSubResult;
    }

    return findInvalidNode(nodeToParent, node, node.right);
  }

  TreeNode remove(TreeNode invalidNode, TreeNode node) {
    return (node == null || node == invalidNode)
        ? null
        : new TreeNode(node.val, remove(invalidNode, node.left), remove(invalidNode, node.right));
  }
}
