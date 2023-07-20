import java.util.ArrayDeque;
import java.util.Deque;

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

class BSTIterator {
  private Deque<Object> stack = new ArrayDeque<>();

  public BSTIterator(TreeNode root) {
    stack.push(root);
    expandToMin();
  }

  public boolean hasNext() {
    return !stack.isEmpty();
  }

  public int next() {
    int result = (int) stack.pop();
    expandToMin();

    return result;
  }

  private void expandToMin() {
    while (!stack.isEmpty() && stack.peek() instanceof TreeNode) {
      TreeNode node = (TreeNode) stack.pop();

      if (node.right != null) {
        stack.push(node.right);
      }
      stack.push(node.val);
      if (node.left != null) {
        stack.push(node.left);
      }
    }
  }
}

// Your BSTIterator object will be instantiated and called as such:
// BSTIterator obj = new BSTIterator(root);
// int param_1 = obj.next();
// boolean param_2 = obj.hasNext();
