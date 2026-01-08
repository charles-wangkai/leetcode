// https://en.wikipedia.org/wiki/Fenwick_tree

class NumArray {
  int[] nums;
  FenwickTree fenwickTree;

  public NumArray(int[] nums) {
    this.nums = nums;
    fenwickTree = new FenwickTree(nums.length);
    for (int i = 0; i < nums.length; ++i) {
      fenwickTree.add(i + 1, nums[i]);
    }
  }

  public void update(int index, int val) {
    fenwickTree.add(index + 1, val - nums[index]);
    nums[index] = val;
  }

  public int sumRange(int left, int right) {
    return fenwickTree.computePrefixSum(right + 1) - fenwickTree.computePrefixSum(left);
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
