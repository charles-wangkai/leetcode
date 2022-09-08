import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
    Stack<Object> stack = new Stack<>();
    stack.push(root);
    while (!stack.empty()) {
      Object top = stack.pop();
      if (top != null) {
        if (top instanceof TreeNode) {
          TreeNode node = (TreeNode) top;
          stack.push(node.right);
          stack.push(node.val);
          stack.push(node.left);
        } else {
          result.add((Integer) top);
        }
      }
    }

    return result;
  }
}
