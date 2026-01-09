class Solution {
  public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
    int result = 0;
    SegTree segTree = new SegTree(baskets);
    for (int fruit : fruits) {
      if (segTree.root.maxValue >= fruit) {
        segTree.update(segTree.findIndex(fruit), 0);
      } else {
        ++result;
      }
    }

    return result;
  }
}

class SegTree {
  Node root;

  SegTree(int[] values) {
    root = buildNode(values, 0, values.length - 1);
  }

  private Node buildNode(int[] values, int beginIndex, int endIndex) {
    if (beginIndex == endIndex) {
      return new Node(beginIndex, endIndex, values[beginIndex], null, null);
    }

    int middleIndex = (beginIndex + endIndex) / 2;
    Node left = buildNode(values, beginIndex, middleIndex);
    Node right = buildNode(values, middleIndex + 1, endIndex);

    return new Node(beginIndex, endIndex, Math.max(left.maxValue, right.maxValue), left, right);
  }

  void update(int index, int value) {
    update(index, value, root);
  }

  private void update(int index, int value, Node node) {
    if (node.beginIndex <= index && node.endIndex >= index) {
      if (node.beginIndex == node.endIndex) {
        node.maxValue = value;
      } else {
        update(index, value, node.left);
        update(index, value, node.right);

        node.maxValue = Math.max(node.left.maxValue, node.right.maxValue);
      }
    }
  }

  int findIndex(int target) {
    return findIndex(target, root);
  }

  private int findIndex(int target, Node node) {
    if (node.beginIndex == node.endIndex) {
      return node.beginIndex;
    }

    return (node.left.maxValue >= target)
        ? findIndex(target, node.left)
        : findIndex(target, node.right);
  }

  static class Node {
    int beginIndex;
    int endIndex;
    int maxValue;
    Node left;
    Node right;

    Node(int beginIndex, int endIndex, int maxValue, Node left, Node right) {
      this.beginIndex = beginIndex;
      this.endIndex = endIndex;
      this.maxValue = maxValue;
      this.left = left;
      this.right = right;
    }
  }
}
