class Solution {
  public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
    Node segmentTree = buildNode(baskets, 0, baskets.length - 1);

    int result = 0;
    for (int fruit : fruits) {
      if (segmentTree.maxValue >= fruit) {
        update(findIndex(fruit, segmentTree), 0, segmentTree);
      } else {
        ++result;
      }
    }

    return result;
  }

  void update(int index, int value, Node node) {
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

  int findIndex(int target, Node node) {
    if (node.beginIndex == node.endIndex) {
      return node.beginIndex;
    }

    return (node.left.maxValue >= target)
        ? findIndex(target, node.left)
        : findIndex(target, node.right);
  }

  Node buildNode(int[] values, int beginIndex, int endIndex) {
    if (beginIndex == endIndex) {
      return new Node(beginIndex, endIndex, values[beginIndex], null, null);
    }

    int middleIndex = (beginIndex + endIndex) / 2;
    Node left = buildNode(values, beginIndex, middleIndex);
    Node right = buildNode(values, middleIndex + 1, endIndex);

    return new Node(beginIndex, endIndex, Math.max(left.maxValue, right.maxValue), left, right);
  }
}

class Node {
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