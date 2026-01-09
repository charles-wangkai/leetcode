// https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-ii/solutions/4219104/segment-tree-with-range-update-o-n-log-n-time-o-n-space/

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int sumCounts(int[] nums) {
    int result = 0;
    LazySegTree lazySegTree = new LazySegTree(nums);
    Map<Integer, Integer> valueToLastIndex = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      int lastIndex = valueToLastIndex.getOrDefault(nums[i], -1);
      lazySegTree.update(lastIndex + 1, i);

      result = MOD_INT.addMod(result, lazySegTree.query(0, i).squareSum());

      valueToLastIndex.put(nums[i], i);
    }

    return result;
  }
}

class LazySegTree {
  Node root;

  LazySegTree(int[] values) {
    root = buildNode(values, 0, values.length - 1);
  }

  private Node buildNode(int[] values, int beginIndex, int endIndex) {
    Node node = new Node(beginIndex, endIndex, 0);

    if (beginIndex == endIndex) {
      node.element = new Element(0, 0);
    } else {
      int middleIndex = (beginIndex + endIndex) / 2;
      node.left = buildNode(values, beginIndex, middleIndex);
      node.right = buildNode(values, middleIndex + 1, endIndex);

      node.pull();
    }

    return node;
  }

  void update(int beginIndex, int endIndex) {
    update(beginIndex, endIndex, root);
  }

  private void update(int beginIndex, int endIndex, Node node) {
    if (!(node.beginIndex > endIndex || node.endIndex < beginIndex)) {
      if (node.beginIndex >= beginIndex && node.endIndex <= endIndex) {
        node.apply(1);
      } else {
        node.pushDown();

        update(beginIndex, endIndex, node.left);
        update(beginIndex, endIndex, node.right);

        node.pull();
      }
    }
  }

  Element query(int beginIndex, int endIndex) {
    return query(beginIndex, endIndex, root);
  }

  private Element query(int beginIndex, int endIndex, Node node) {
    if (node.beginIndex > endIndex || node.endIndex < beginIndex) {
      return new Element(0, 0);
    }
    if (node.beginIndex >= beginIndex && node.endIndex <= endIndex) {
      return node.getComputed();
    }

    node.pushDown();

    node.pull();

    return Element.merge(
        query(beginIndex, endIndex, node.left), query(beginIndex, endIndex, node.right));
  }

  static class Node {
    static final ModInt MOD_INT = Solution.MOD_INT;

    int beginIndex;
    int endIndex;
    int delta;
    Element element;
    Node left;
    Node right;

    Node(int beginIndex, int endIndex, int delta) {
      this.beginIndex = beginIndex;
      this.endIndex = endIndex;
      this.delta = delta;
    }

    Element getComputed() {
      return new Element(
          MOD_INT.addMod(
              element.squareSum,
              MOD_INT.addMod(
                  MOD_INT.multiplyMod(MOD_INT.multiplyMod(2, element.sum), delta),
                  MOD_INT.multiplyMod(
                      endIndex - beginIndex + 1, MOD_INT.multiplyMod(delta, delta)))),
          MOD_INT.addMod(element.sum, MOD_INT.multiplyMod(endIndex - beginIndex + 1, delta)));
    }

    void pushDown() {
      if (delta != 0) {
        left.apply(delta);
        right.apply(delta);

        delta = 0;
      }
    }

    void apply(int d) {
      delta += d;
    }

    void pull() {
      element = Element.merge(left.getComputed(), right.getComputed());
    }
  }

  record Element(int squareSum, int sum) {
    static final ModInt MOD_INT = Solution.MOD_INT;

    static Element merge(Element leftElement, Element rightElement) {
      return new Element(
          MOD_INT.addMod(leftElement.squareSum, rightElement.squareSum),
          MOD_INT.addMod(leftElement.sum, rightElement.sum));
    }
  }
}

class ModInt {
  int modulus;

  ModInt(int modulus) {
    this.modulus = modulus;
  }

  int mod(long x) {
    return (int) (x % modulus);
  }

  int modInv(int x) {
    return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(modulus)).intValue();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, modulus);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, modulus);
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, modInv(y));
  }

  int powMod(int base, int exponent) {
    if (exponent == 0) {
      return 1;
    }

    return multiplyMod(
        (exponent % 2 == 0) ? 1 : base, powMod(multiplyMod(base, base), exponent / 2));
  }
}
