import java.util.ArrayDeque;
import java.util.Deque;

// Definition for binary tree
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

public class Solution {
  public TreeNode UpsideDownBinaryTree(TreeNode root) {
    Deque<TreeNode> stack = new ArrayDeque<>();
    for (TreeNode node = root; node != null; node = node.left) {
      stack.push(node);
    }

    TreeNode result = null;
    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      if (result == null) {
        result = node;
      }
      if (stack.isEmpty()) {
        node.left = null;
        node.right = null;
      } else {
        node.left = stack.peek().right;
        node.right = stack.peek();
      }
    }

    return result;
  }
}
