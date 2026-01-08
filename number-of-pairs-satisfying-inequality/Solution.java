class Solution {
  static final int LIMIT = 60000;
  static final int OFFSET = 30000;

  public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
    long result = 0;
    FenwickTree fenwickTree = new FenwickTree(LIMIT);
    for (int i = 0; i < nums1.length; ++i) {
      int delta = nums1[i] - nums2[i];
      result += fenwickTree.computePrefixSum(delta + diff + OFFSET + 1);
      fenwickTree.add(delta + OFFSET + 1, 1);
    }

    return result;
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
