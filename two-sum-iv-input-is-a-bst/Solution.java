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

class Solution {
  public boolean findTarget(TreeNode root, int k) {
    Deque<Object> lowers = new ArrayDeque<>();
    lowers.push(root);
    int lower = leftScan(lowers);

    Deque<Object> uppers = new ArrayDeque<>();
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

  int leftScan(Deque<Object> lowers) {
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

  int rightScan(Deque<Object> uppers) {
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
