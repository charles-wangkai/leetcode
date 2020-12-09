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

class BSTIterator {
  private Stack<Object> stack = new Stack<>();

  public BSTIterator(TreeNode root) {
    stack.push(root);
    expandToMin();
  }

  public boolean hasNext() {
    return !stack.empty();
  }

  public int next() {
    int result = (int) stack.pop();
    expandToMin();

    return result;
  }

  private void expandToMin() {
    while (!stack.empty() && !(stack.peek() instanceof Integer)) {
      Object top = stack.pop();
      if (top != null) {
        TreeNode node = (TreeNode) top;
        stack.push(node.right);
        stack.push(node.val);
        stack.push(node.left);
      }
    }
  }
}

// Your BSTIterator object will be instantiated and called as such:
// BSTIterator obj = new BSTIterator(root);
// int param_1 = obj.next();
// boolean param_2 = obj.hasNext();
