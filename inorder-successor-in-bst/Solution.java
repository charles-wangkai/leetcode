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

class Solution {
  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    TreeNode successor;
    if (p.right == null) {
      Deque<TreeNode> path = new ArrayDeque<>();
      searchPath(path, p, root);

      TreeNode node = path.pop();
      while (!path.isEmpty() && path.peek().left != node) {
        node = path.pop();
      }

      successor = path.isEmpty() ? null : path.peek();
    } else {
      successor = p.right;
      while (successor.left != null) {
        successor = successor.left;
      }
    }

    return successor;
  }

  void searchPath(Deque<TreeNode> path, TreeNode p, TreeNode node) {
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
