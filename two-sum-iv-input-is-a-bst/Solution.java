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
  public boolean findTarget(TreeNode root, int k) {
    Stack<Object> lowers = new Stack<>();
    lowers.push(root);
    int lower = leftScan(lowers);

    Stack<Object> uppers = new Stack<>();
    uppers.push(root);
    int upper = rightScan(uppers);

    while (lower != upper) {
      int sum = lower + upper;
      if (sum > k) {
        upper = rightScan(uppers);
      } else if (sum < k) {
        lower = leftScan(lowers);
      } else {
        return true;
      }
    }

    return false;
  }

  int leftScan(Stack<Object> lowers) {
    while (true) {
      Object head = lowers.pop();
      if (head instanceof Integer value) {
        return value;
      }

      TreeNode node = (TreeNode) head;

      if (node.right != null) {
        lowers.push(node.right);
      }
      lowers.push(node.val);
      if (node.left != null) {
        lowers.push(node.left);
      }
    }
  }

  int rightScan(Stack<Object> uppers) {
    while (true) {
      Object head = uppers.pop();
      if (head instanceof Integer value) {
        return value;
      }

      TreeNode node = (TreeNode) head;

      if (node.left != null) {
        uppers.push(node.left);
      }
      uppers.push(node.val);
      if (node.right != null) {
        uppers.push(node.right);
      }
    }
  }
}
