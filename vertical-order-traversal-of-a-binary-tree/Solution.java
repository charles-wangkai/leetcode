import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
  public List<List<Integer>> verticalTraversal(TreeNode root) {
    List<Element> elements = new ArrayList<>();
    search(elements, 0, 0, root);

    Collections.sort(
        elements,
        Comparator.comparing((Element e) -> e.x)
            .thenComparing(e -> e.y, Comparator.reverseOrder())
            .thenComparing(e -> e.val));

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> report = null;
    for (int i = 0; i <= elements.size(); ++i) {
      if (i != 0 && i != elements.size() && elements.get(i).x == elements.get(i - 1).x) {
        report.add(elements.get(i).val);
      } else {
        if (report != null) {
          result.add(report);
        }

        if (i != elements.size()) {
          report = new ArrayList<>();
          report.add(elements.get(i).val);
        }
      }
    }

    return result;
  }

  void search(List<Element> elements, int x, int y, TreeNode node) {
    elements.add(new Element(x, y, node.val));

    if (node.left != null) {
      search(elements, x - 1, y - 1, node.left);
    }
    if (node.right != null) {
      search(elements, x + 1, y - 1, node.right);
    }
  }
}

class Element {
  int x;
  int y;
  int val;

  Element(int x, int y, int val) {
    this.x = x;
    this.y = y;
    this.val = val;
  }
}
