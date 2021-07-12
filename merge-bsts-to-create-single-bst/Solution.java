import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
  public TreeNode canMerge(List<TreeNode> trees) {
    if (hasDuplicate(trees)) {
      return null;
    }

    int totalNodeNum = trees.stream().mapToInt(this::countNodes).sum();

    Map<Integer, TreeNode> rootValueToNode = new HashMap<>();
    for (TreeNode tree : trees) {
      rootValueToNode.put(tree.val, tree);
    }

    Map<Integer, TreeNode> leafValueToParent = new HashMap<>();
    for (TreeNode tree : trees) {
      if (tree.left != null || tree.right != null) {
        buildLeafValueToParent(leafValueToParent, tree, null);
      }
    }

    for (int leafValue : leafValueToParent.keySet()) {
      TreeNode parent = leafValueToParent.get(leafValue);

      if (parent != null && rootValueToNode.containsKey(leafValue)) {
        if (parent.left != null && parent.left.val == leafValue) {
          parent.left = rootValueToNode.remove(leafValue);
        } else {
          parent.right = rootValueToNode.remove(leafValue);
        }
      }
    }

    if (rootValueToNode.size() != 1) {
      return null;
    }

    TreeNode root = rootValueToNode.values().iterator().next();

    return (checkBST(root).bst && countNodes(root) == totalNodeNum - trees.size() + 1)
        ? root
        : null;
  }

  int countNodes(TreeNode node) {
    if (node == null) {
      return 0;
    }

    return 1 + countNodes(node.left) + countNodes(node.right);
  }

  Element checkBST(TreeNode node) {
    int minValue = node.val;
    int maxValue = node.val;

    if (node.left != null) {
      Element leftSubResult = checkBST(node.left);
      if (!leftSubResult.bst || leftSubResult.maxValue >= node.val) {
        return new Element(0, 0, false);
      }

      minValue = leftSubResult.minValue;
    }
    if (node.right != null) {
      Element rightSubResult = checkBST(node.right);
      if (!rightSubResult.bst || rightSubResult.minValue <= node.val) {
        return new Element(0, 0, false);
      }

      maxValue = rightSubResult.maxValue;
    }

    return new Element(minValue, maxValue, true);
  }

  void buildLeafValueToParent(
      Map<Integer, TreeNode> leafValueToParent, TreeNode node, TreeNode parent) {
    if (node.left == null && node.right == null) {
      leafValueToParent.put(node.val, parent);
    }

    if (node.left != null) {
      buildLeafValueToParent(leafValueToParent, node.left, node);
    }
    if (node.right != null) {
      buildLeafValueToParent(leafValueToParent, node.right, node);
    }
  }

  boolean hasDuplicate(List<TreeNode> trees) {
    Set<Integer> values = new HashSet<>();
    for (TreeNode tree : trees) {
      if (hasSameValue(values, tree.left) || hasSameValue(values, tree.right)) {
        return true;
      }
    }

    return false;
  }

  boolean hasSameValue(Set<Integer> values, TreeNode node) {
    if (node == null) {
      return false;
    }
    if (values.contains(node.val)) {
      return true;
    }

    values.add(node.val);

    return hasSameValue(values, node.left) || hasSameValue(values, node.right);
  }
}

class Element {
  int minValue;
  int maxValue;
  boolean bst;

  Element(int minValue, int maxValue, boolean bst) {
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.bst = bst;
  }
}
