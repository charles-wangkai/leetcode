import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
  public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
    List<Integer> values = new ArrayList<>();
    search(values, root);

    return queries.stream()
        .map(
            query -> {
              int min;
              int max;
              int index = Collections.binarySearch(values, query);
              if (index >= 0) {
                min = query;
                max = query;
              } else {
                index = -1 - index;

                min = (index == 0) ? -1 : values.get(index - 1);
                max = (index == values.size()) ? -1 : values.get(index);
              }

              return List.of(min, max);
            })
        .toList();
  }

  void search(List<Integer> values, TreeNode node) {
    if (node != null) {
      search(values, node.left);
      values.add(node.val);
      search(values, node.right);
    }
  }
}
