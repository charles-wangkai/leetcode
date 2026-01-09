// https://leetcode.com/problems/find-x-value-of-array-ii/solutions/6668988/detailed-explanation-why-segment-tree-in-seha/

class Solution {
  public int[] resultArray(int[] nums, int k, int[][] queries) {
    int[] result = new int[queries.length];
    SegTree segTree = new SegTree(nums, k);
    for (int i = 0; i < result.length; ++i) {
      segTree.update(queries[i][0], queries[i][1]);
      result[i] = segTree.query(queries[i][2], nums.length - 1).prefixProductNums[queries[i][3]];
    }

    return result;
  }
}

class SegTree {
  int k;
  Node root;

  SegTree(int[] values, int k) {
    this.k = k;
    root = buildNode(values, 0, values.length - 1);
  }

  private Node buildNode(int[] values, int beginIndex, int endIndex) {
    if (beginIndex == endIndex) {
      int product = values[beginIndex] % k;

      int[] prefixProductNums = new int[k];
      prefixProductNums[product] = 1;

      return new Node(beginIndex, endIndex, new Element(product, prefixProductNums), null, null);
    }

    int middleIndex = (beginIndex + endIndex) / 2;
    Node left = buildNode(values, beginIndex, middleIndex);
    Node right = buildNode(values, middleIndex + 1, endIndex);

    return new Node(beginIndex, endIndex, Element.merge(left.element, right.element), left, right);
  }

  void update(int index, int value) {
    update(index, value, root);
  }

  private void update(int index, int value, Node node) {
    if (node.beginIndex <= index && node.endIndex >= index) {
      if (node.beginIndex == node.endIndex) {
        int product = value % k;

        int[] prefixProductNums = new int[k];
        prefixProductNums[product] = 1;

        node.element = new Element(product, prefixProductNums);
      } else {
        update(index, value, node.left);
        update(index, value, node.right);

        node.element = Element.merge(node.left.element, node.right.element);
      }
    }
  }

  public Element query(int beginIndex, int endIndex) {
    return query(beginIndex, endIndex, root);
  }

  private Element query(int beginIndex, int endIndex, Node node) {
    if (node.beginIndex > endIndex || node.endIndex < beginIndex) {
      return new Element(1, new int[k]);
    }
    if (node.beginIndex >= beginIndex && node.endIndex <= endIndex) {
      return node.element;
    }

    return Element.merge(
        query(beginIndex, endIndex, node.left), query(beginIndex, endIndex, node.right));
  }

  static class Node {
    int beginIndex;
    int endIndex;
    Element element;
    Node left;
    Node right;

    Node(int beginIndex, int endIndex, Element element, Node left, Node right) {
      this.beginIndex = beginIndex;
      this.endIndex = endIndex;
      this.element = element;
      this.left = left;
      this.right = right;
    }
  }

  static class Element {
    int product;
    int[] prefixProductNums;

    Element(int product, int[] prefixProductNums) {
      this.product = product;
      this.prefixProductNums = prefixProductNums;
    }

    static Element merge(Element leftElement, Element rightElement) {
      int k = leftElement.prefixProductNums.length;

      int product = leftElement.product * rightElement.product % k;

      int[] prefixProductNums = leftElement.prefixProductNums.clone();
      for (int i = 0; i < k; ++i) {
        prefixProductNums[leftElement.product * i % k] += rightElement.prefixProductNums[i];
      }

      return new Element(product, prefixProductNums);
    }
  }
}
