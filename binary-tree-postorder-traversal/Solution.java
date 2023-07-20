import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    Deque<Object> stack = new ArrayDeque<>();
    if (root != null) {
      stack.push(root);
    }
    while (!stack.isEmpty()) {
      Object element = stack.pop();
      if (element == null) {
        continue;
      }
      if (element instanceof Integer value) {
        result.add(value);
      } else {
        TreeNode node = (TreeNode) element;
        stack.push(node.val);
        if (node.right != null) {
          stack.push(node.right);
        }
        if (node.left != null) {
          stack.push(node.left);
        }
      }
    }
    return result;
  }
}
