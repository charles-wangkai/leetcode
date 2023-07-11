import java.util.ArrayList;
import java.util.List;

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
  public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
    List<Integer> solutionNodes = new ArrayList<>();

    search(solutionNodes, target, k);

    List<TreeNode> path = findPath(target, root);
    for (int i = 1; i < path.size() && i < k; ++i) {
      TreeNode start = (path.get(i).left == path.get(i - 1)) ? path.get(i).right : path.get(i).left;
      search(solutionNodes, start, k - i - 1);
    }
    if (k != 0 && k < path.size()) {
      solutionNodes.add(path.get(k).val);
    }

    return solutionNodes;
  }

  void search(List<Integer> solutionNodes, TreeNode node, int distance) {
    if (node != null) {
      if (distance == 0) {
        solutionNodes.add(node.val);
      } else {
        search(solutionNodes, node.left, distance - 1);
        search(solutionNodes, node.right, distance - 1);
      }
    }
  }

  List<TreeNode> findPath(TreeNode target, TreeNode node) {
    if (node == null) {
      return null;
    }

    List<TreeNode> result;
    if (node == target) {
      result = new ArrayList<>();
      result.add(node);
    } else {
      result = findPath(target, node.left);
      if (result == null) {
        result = findPath(target, node.right);
      }

      if (result != null) {
        result.add(node);
      }
    }

    return result;
  }
}
