// Definition for a binary tree node.

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
  public List<Long> zigzagLevelSum(TreeNode root) {
    List<List<TreeNode>> nodeLists = new ArrayList<>();
    search(nodeLists, 0, root);

    return IntStream.range(0, nodeLists.size())
        .mapToLong(
            level -> {
              List<TreeNode> nodeList = nodeLists.get(level);

              long levelSum = 0;
              if (level % 2 == 0) {
                for (int i = 0; i < nodeList.size() && nodeList.get(i).left != null; ++i) {
                  levelSum += nodeList.get(i).val;
                }
              } else {
                for (int i = nodeList.size() - 1; i >= 0 && nodeList.get(i).right != null; --i) {
                  levelSum += nodeList.get(i).val;
                }
              }

              return levelSum;
            })
        .boxed()
        .toList();
  }

  void search(List<List<TreeNode>> nodeLists, int level, TreeNode node) {
    if (level == nodeLists.size()) {
      nodeLists.add(new ArrayList<>());
    }
    nodeLists.get(level).add(node);

    if (node.left != null) {
      search(nodeLists, level + 1, node.left);
    }
    if (node.right != null) {
      search(nodeLists, level + 1, node.right);
    }
  }
}