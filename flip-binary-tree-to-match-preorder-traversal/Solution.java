import java.util.ArrayList;
import java.util.Arrays;
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
  public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
    List<Integer> result = search(root, Arrays.stream(voyage).boxed().toList());

    return (result == null) ? List.of(-1) : result;
  }

  List<Integer> search(TreeNode node, List<Integer> voyage) {
    if (node == null) {
      return voyage.isEmpty() ? List.of() : null;
    }

    if (voyage.isEmpty() || voyage.get(0) != node.val) {
      return null;
    }

    if (node.left == null) {
      return search(node.right, voyage.subList(1, voyage.size()));
    }
    if (node.right == null) {
      return search(node.left, voyage.subList(1, voyage.size()));
    }

    int leftBeginIndex = voyage.indexOf(node.left.val);
    if (leftBeginIndex == -1) {
      return null;
    }

    int rightBeginIndex = voyage.indexOf(node.right.val);
    if (rightBeginIndex == -1) {
      return null;
    }

    int leftEndIndex;
    int rightEndIndex;
    if (leftBeginIndex < rightBeginIndex) {
      leftEndIndex = rightBeginIndex;
      rightEndIndex = voyage.size();
    } else {
      rightEndIndex = leftBeginIndex;
      leftEndIndex = voyage.size();
    }

    List<Integer> leftResult = search(node.left, voyage.subList(leftBeginIndex, leftEndIndex));
    List<Integer> rightResult = search(node.right, voyage.subList(rightBeginIndex, rightEndIndex));

    if (leftResult == null || rightResult == null) {
      return null;
    }

    List<Integer> result = new ArrayList<>();
    if (leftBeginIndex > rightBeginIndex) {
      result.add(node.val);
    }
    result.addAll(leftResult);
    result.addAll(rightResult);

    return result;
  }
}
