// https://en.wikipedia.org/wiki/Fenwick_tree

class NumArray {
  int[] nums;
  int[] A;

  public NumArray(int[] nums) {
    this.nums = nums;
    A = new int[Integer.highestOneBit(nums.length) * 2 + 1];
    for (int i = 0; i < nums.length; ++i) {
      add(i, nums[i]);
    }
  }

  public void update(int index, int val) {
    add(index, val - nums[index]);
    nums[index] = val;
  }

  public int sumRange(int left, int right) {
    return prefix_sum(right) - ((left == 0) ? 0 : prefix_sum(left - 1));
  }

  int LSB(int i) {
    return i & -i;
  }

  int prefix_sum(int i) {
    int sum = A[0];
    for (; i != 0; i -= LSB(i)) sum += A[i];
    return sum;
  }

  void add(int i, int delta) {
    if (i == 0) {
      A[0] += delta;
      return;
    }
    for (; i < A.length; i += LSB(i)) A[i] += delta;
  }
}

// Your NumArray object will be instantiated and called as such:
// NumArray obj = new NumArray(nums);
// obj.update(index,val);
// int param_2 = obj.sumRange(left,right);
