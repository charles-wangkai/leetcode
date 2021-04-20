import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

// Definition for a binary tree node.
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
    Map<Integer, Property> valueToProperty = new HashMap<>();
    search(valueToProperty, root, null, 0);

    SortedSet<Integer> ancestors =
        new TreeSet<>(
            Comparator.comparing((Integer value) -> valueToProperty.get(value).depth)
                .reversed()
                .thenComparing(value -> value));
    for (TreeNode node : nodes) {
      ancestors.add(node.val);
    }

    while (ancestors.size() != 1) {
      int first = ancestors.first();
      ancestors.remove(first);

      ancestors.add(valueToProperty.get(first).parent);
    }

    return valueToProperty.get(ancestors.first()).node;
  }

  void search(Map<Integer, Property> valueToProperty, TreeNode node, Integer parent, int depth) {
    valueToProperty.put(node.val, new Property(node, depth, parent));

    if (node.left != null) {
      search(valueToProperty, node.left, node.val, depth + 1);
    }
    if (node.right != null) {
      search(valueToProperty, node.right, node.val, depth + 1);
    }
  }
}

class Property {
  TreeNode node;
  int depth;
  Integer parent;

  Property(TreeNode node, int depth, Integer parent) {
    this.node = node;
    this.depth = depth;
    this.parent = parent;
  }
}
