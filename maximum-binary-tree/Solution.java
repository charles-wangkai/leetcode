import java.util.ArrayDeque;
import java.util.Deque;

// Definition for a binary tree node.
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

public class Solution {
  public TreeNode constructMaximumBinaryTree(int[] nums) {
    TreeNode root = null;
    Deque<TreeNode> stack = new ArrayDeque<>();
    for (int num : nums) {
      TreeNode node = new TreeNode(num);

      while (!stack.isEmpty() && num > stack.peek().val) {
        stack.pop();
      }

      if (stack.isEmpty()) {
        node.left = root;
        root = node;
      } else {
        node.left = stack.peek().right;
        stack.peek().right = node;
      }
      stack.push(node);
    }
    return root;
  }
}
