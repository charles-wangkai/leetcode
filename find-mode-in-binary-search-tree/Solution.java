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

public class Solution {
  List<Integer> modes;
  int modeFreq;
  Integer current;
  int currentFreq;

  public int[] findMode(TreeNode root) {
    if (root == null) {
      return new int[0];
    }

    modeFreq = 0;
    current = null;

    search(root);
    updateModes();

    return modes.stream().mapToInt(x -> x).toArray();
  }

  void search(TreeNode node) {
    if (node.left != null) {
      search(node.left);
    }

    if (current != null && node.val == current) {
      currentFreq++;
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
    if (currentFreq > modeFreq) {
      modes = new ArrayList<>();
      modes.add(current);

      modeFreq = currentFreq;
    } else if (currentFreq == modeFreq) {
      modes.add(current);
    }
  }
}
