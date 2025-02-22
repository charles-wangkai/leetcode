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

class Solution {
  int elementIndex;

  public TreeNode recoverFromPreorder(String traversal) {
    List<Element> elements = buildElements(traversal);
    elementIndex = 0;

    return buildNode(elements, 0);
  }

  List<Element> buildElements(String traversal) {
    List<Element> result = new ArrayList<>();
    int depth = 0;
    int value = 0;
    for (int i = 0; i <= traversal.length(); ++i) {
      if (i == traversal.length() || traversal.charAt(i) == '-') {
        if (value != 0) {
          result.add(new Element(depth, value));

          depth = 0;
          value = 0;
        }

        ++depth;
      } else {
        value = value * 10 + (traversal.charAt(i) - '0');
      }
    }

    return result;
  }

  TreeNode buildNode(List<Element> elements, int depth) {
    if (elementIndex == elements.size() || elements.get(elementIndex).depth() != depth) {
      return null;
    }

    TreeNode node = new TreeNode(elements.get(elementIndex).value());
    ++elementIndex;

    node.left = buildNode(elements, depth + 1);
    node.right = buildNode(elements, depth + 1);

    return node;
  }
}

record Element(int depth, int value) {}
