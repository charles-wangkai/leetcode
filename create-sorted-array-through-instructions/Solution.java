// https://en.wikipedia.org/wiki/Fenwick_tree

class Solution {
  static final int MODULUS = 1_000_000_007;
  static final int LIMIT = 100000;

  public int createSortedArray(int[] instructions) {
    int result = 0;
    FenwickTree fenwickTree = new FenwickTree(LIMIT);
    for (int i = 0; i < instructions.length; ++i) {
      result =
          addMod(
              result,
              Math.min(
                  fenwickTree.computePrefixSum(instructions[i] - 1),
                  i - fenwickTree.computePrefixSum(instructions[i])));
      fenwickTree.add(instructions[i], 1);
    }

    return result;
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }
}

class FenwickTree {
  int[] a;

  FenwickTree(int size) {
    a = new int[Integer.highestOneBit(size) * 2 + 1];
  }

  void add(int pos, int delta) {
    while (pos < a.length) {
      a[pos] += delta;
      pos += pos & -pos;
    }
  }

  int computePrefixSum(int pos) {
    int result = 0;
    while (pos != 0) {
      result += a[pos];
      pos -= pos & -pos;
    }

    return result;
  }
}
