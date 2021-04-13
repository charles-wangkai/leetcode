import java.util.Stack;

// Definition for a binary tree node.
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

class Solution {
  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    TreeNode successor;
    if (p.right == null) {
      Stack<TreeNode> path = new Stack<>();
      searchPath(path, p, root);

      TreeNode node = path.pop();
      while (!path.empty() && path.peek().left != node) {
        node = path.pop();
      }

      successor = path.empty() ? null : path.peek();
    } else {
      successor = p.right;
      while (successor.left != null) {
        successor = successor.left;
      }
    }

    return successor;
  }

  void searchPath(Stack<TreeNode> path, TreeNode p, TreeNode node) {
    path.push(node);

    if (path.peek() != p && node.left != null) {
      searchPath(path, p, node.left);
    }
    if (path.peek() != p && node.right != null) {
      searchPath(path, p, node.right);
    }

    if (path.peek() != p) {
      path.pop();
    }
  }
}
