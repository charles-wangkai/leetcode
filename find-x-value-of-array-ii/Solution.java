// https://leetcode.com/problems/find-x-value-of-array-ii/solutions/6668988/detailed-explanation-why-segment-tree-in-seha/

class Solution {
  public int[] resultArray(int[] nums, int k, int[][] queries) {
    Node segmentTree = buildNode(nums, k, 0, nums.length - 1);

    int[] result = new int[queries.length];
    for (int i = 0; i < result.length; ++i) {
      update(queries[i][0], queries[i][1], segmentTree);
      result[i] =
          query(queries[i][2], nums.length - 1, segmentTree).prefixProductNums[queries[i][3]];
    }

    return result;
  }

  Element query(int beginIndex, int endIndex, Node node) {
    int k = node.element.prefixProductNums.length;

    if (node.beginIndex > endIndex || node.endIndex < beginIndex) {
      return new Element(1, new int[k]);
    }
    if (node.beginIndex >= beginIndex && node.endIndex <= endIndex) {
      return node.element;
    }

    return merge(query(beginIndex, endIndex, node.left), query(beginIndex, endIndex, node.right));
  }

  void update(int index, int value, Node node) {
    int k = node.element.prefixProductNums.length;

    if (node.beginIndex <= index && node.endIndex >= index) {
      if (node.beginIndex == node.endIndex) {
        int product = value % k;

        int[] prefixProductNums = new int[k];
        prefixProductNums[product] = 1;

        node.element = new Element(product, prefixProductNums);
      } else {
        update(index, value, node.left);
        update(index, value, node.right);

        node.element = merge(node.left.element, node.right.element);
      }
    }
  }

  Node buildNode(int[] nums, int k, int beginIndex, int endIndex) {
    if (beginIndex == endIndex) {
      int product = nums[beginIndex] % k;

      int[] prefixProductNums = new int[k];
      prefixProductNums[product] = 1;

      return new Node(beginIndex, endIndex, new Element(product, prefixProductNums), null, null);
    }

    int middleIndex = (beginIndex + endIndex) / 2;

    Node left = buildNode(nums, k, beginIndex, middleIndex);
    Node right = buildNode(nums, k, middleIndex + 1, endIndex);

    return new Node(beginIndex, endIndex, merge(left.element, right.element), left, right);
  }

  Element merge(Element leftElement, Element rightElement) {
    int k = leftElement.prefixProductNums.length;

    int product = leftElement.product * rightElement.product % k;

    int[] prefixProductNums = leftElement.prefixProductNums.clone();
    for (int i = 0; i < k; ++i) {
      prefixProductNums[leftElement.product * i % k] += rightElement.prefixProductNums[i];
    }

    return new Element(product, prefixProductNums);
  }
}

class Node {
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

class Element {
  int product;
  int[] prefixProductNums;

  Element(int product, int[] prefixProductNums) {
    this.product = product;
    this.prefixProductNums = prefixProductNums;
  }
}
