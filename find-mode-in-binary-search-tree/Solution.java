import java.util.ArrayList;
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

public class Solution {
  List<Integer> modes = new ArrayList<>();
  int modeFreq;
  Integer current;
  int currentFreq;

  public int[] findMode(TreeNode root) {
    modeFreq = 0;
    current = null;

    search(root);
    updateModes();

    return modes.stream().mapToInt(Integer::intValue).toArray();
  }

  void search(TreeNode node) {
    if (node.left != null) {
      search(node.left);
    }

    if (current != null && node.val == current) {
      ++currentFreq;
    } else {
      if (current != null) {
        updateModes();
      }

      current = node.val;
      currentFreq = 1;
    }

    if (node.right != null) {
      search(node.right);
    }
  }

  void updateModes() {
    if (currentFreq >= modeFreq) {
      if (currentFreq > modeFreq) {
        modes.clear();
      }
      modes.add(current);

      modeFreq = currentFreq;
    }
  }
}
