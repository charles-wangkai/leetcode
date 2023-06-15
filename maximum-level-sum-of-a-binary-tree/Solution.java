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
  public int maxLevelSum(TreeNode root) {
    List<Integer> sums = new ArrayList<>();
    search(sums, root, 0);

    int maxSum = sums.stream().mapToInt(Integer::intValue).max().getAsInt();

    return IntStream.range(0, sums.size()).filter(i -> sums.get(i) == maxSum).findFirst().getAsInt()
        + 1;
  }

  void search(List<Integer> sums, TreeNode node, int index) {
    if (node != null) {
      if (index == sums.size()) {
        sums.add(0);
      }
      sums.set(index, sums.get(index) + node.val);

      search(sums, node.left, index + 1);
      search(sums, node.right, index + 1);
    }
  }
}
