class Solution {
  static final int LIMIT = 100005;

  public long countMajoritySubarrays(int[] nums, int target) {
    FenwickTree fenwickTree = new FenwickTree(LIMIT * 2);
    fenwickTree.add(getIndex(0), 1);

    long result = 0;
    int diff = 0;
    for (int num : nums) {
      diff += (num == target) ? 1 : -1;
      result += fenwickTree.computePrefixSum(getIndex(diff - 1));
      fenwickTree.add(getIndex(diff), 1);
    }

    return result;
  }

  int getIndex(int diff) {
    return diff + LIMIT;
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
