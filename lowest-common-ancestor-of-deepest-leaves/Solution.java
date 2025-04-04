import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  Map<TreeNode, List<TreeNode>> nodeToPath;
  int maxDepth;
  List<TreeNode> leavesWithMaxDepth = new ArrayList<>();

  public TreeNode lcaDeepestLeaves(TreeNode root) {
    nodeToPath = new HashMap<>();
    maxDepth = -1;
    leavesWithMaxDepth.clear();
    search(root, new ArrayList<>());

    return leavesWithMaxDepth.stream().reduce(this::findLca).get();
  }

  void search(TreeNode node, List<TreeNode> path) {
    if (node.left == null && node.right == null) {
      int depth = path.size();
      if (depth == maxDepth) {
        leavesWithMaxDepth.add(node);
      } else if (depth > maxDepth) {
        maxDepth = depth;

        leavesWithMaxDepth.clear();
        leavesWithMaxDepth.add(node);
      }
    }

    path.add(node);

    nodeToPath.put(node, List.copyOf(path));

    if (node.left != null) {
      search(node.left, path);
    }
    if (node.right != null) {
      search(node.right, path);
    }

    path.removeLast();
  }

  TreeNode findLca(TreeNode node1, TreeNode node2) {
    List<TreeNode> path1 = nodeToPath.get(node1);
    List<TreeNode> path2 = nodeToPath.get(node2);

    int index = 0;
    while (index != path1.size() - 1
        && index != path2.size() - 1
        && path1.get(index + 1) == path2.get(index + 1)) {
      ++index;
    }

    return path1.get(index);
  }
}
