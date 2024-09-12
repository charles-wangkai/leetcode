import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
  public int minimumLevel(TreeNode root) {
    List<Long> sums = new ArrayList<>();
    search(sums, root, 0);

    long minSum = sums.stream().mapToLong(Long::longValue).min().getAsLong();

    return IntStream.range(0, sums.size()).filter(i -> sums.get(i) == minSum).min().getAsInt() + 1;
  }

  void search(List<Long> sums, TreeNode node, int index) {
    if (index == sums.size()) {
      sums.add(0L);
    }
    sums.set(index, sums.get(index) + node.val);

    if (node.left != null) {
      search(sums, node.left, index + 1);
    }
    if (node.right != null) {
      search(sums, node.right, index + 1);
    }
  }
}