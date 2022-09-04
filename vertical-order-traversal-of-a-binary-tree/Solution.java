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
        Comparator.comparing((Element e) -> e.c())
            .thenComparing(e -> e.r())
            .thenComparing(e -> e.value()));

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> column = null;
    for (int i = 0; i <= elements.size(); ++i) {
      if (i != 0 && i != elements.size() && elements.get(i).c() == elements.get(i - 1).c()) {
        column.add(elements.get(i).value());
      } else {
        if (column != null) {
          result.add(column);
        }

        if (i != elements.size()) {
          column = new ArrayList<>();
          column.add(elements.get(i).value());
        }
      }
    }

    return result;
  }

  void search(List<Element> elements, int r, int c, TreeNode node) {
    elements.add(new Element(r, c, node.val));

    if (node.left != null) {
      search(elements, r + 1, c - 1, node.left);
    }
    if (node.right != null) {
      search(elements, r + 1, c + 1, node.right);
    }
  }
}

record Element(int r, int c, int value) {}
