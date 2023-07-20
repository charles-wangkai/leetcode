import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    Deque<Object> stack = new ArrayDeque<>();
    if (root != null) {
      stack.push(root);
    }
    while (!stack.isEmpty()) {
      Object top = stack.pop();
      if (top instanceof TreeNode node) {
        if (node.right != null) {
          stack.push(node.right);
        }
        stack.push(node.val);
        if (node.left != null) {
          stack.push(node.left);
        }
      } else {
        result.add((Integer) top);
      }
    }

    return result;
  }
}
