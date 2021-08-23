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
    Stack<Element> lowers = new Stack<>();
    lowers.push(new Element(root, true));
    TreeNode lower = leftScan(lowers);

    Stack<Element> uppers = new Stack<>();
    uppers.push(new Element(root, true));
    TreeNode upper = rightScan(uppers);

    while (lower != upper) {
      int sum = lower.val + upper.val;
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

  TreeNode leftScan(Stack<Element> lowers) {
    while (true) {
      Element head = lowers.pop();
      if (!head.toExpand) {
        return head.node;
      }

      if (head.node.right != null) {
        lowers.push(new Element(head.node.right, true));
      }
      lowers.push(new Element(head.node, false));
      if (head.node.left != null) {
        lowers.push(new Element(head.node.left, true));
      }
    }
  }

  TreeNode rightScan(Stack<Element> uppers) {
    while (true) {
      Element head = uppers.pop();
      if (!head.toExpand) {
        return head.node;
      }

      if (head.node.left != null) {
        uppers.push(new Element(head.node.left, true));
      }
      uppers.push(new Element(head.node, false));
      if (head.node.right != null) {
        uppers.push(new Element(head.node.right, true));
      }
    }
  }
}

class Element {
  TreeNode node;
  boolean toExpand;

  Element(TreeNode node, boolean toExpand) {
    this.node = node;
    this.toExpand = toExpand;
  }
}
