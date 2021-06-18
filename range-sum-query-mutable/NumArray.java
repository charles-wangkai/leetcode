// https://en.wikipedia.org/wiki/Fenwick_tree

class NumArray {
  int[] A;

  public NumArray(int[] nums) {
    A = new int[nums.length];
    for (int i = 0; i < A.length; ++i) {
      update(i, nums[i]);
    }
  }

  public void update(int index, int val) {
    add(index, val - get(index));
  }

  public int sumRange(int left, int right) {
    return range_sum(left, right + 1);
  }

  int LSBIT(int i) {
    return i & (-i);
  }

  void add(int i, int delta) {
    assert (0 <= i && i < A.length);
    for (; i < A.length; i += LSBIT(i + 1)) A[i] += delta;
  }

  int get(int i) {
    return range_sum(i, i + 1);
  }

  int range_sum(int i, int j) {
    int sum = 0;
    assert (0 <= i && i <= j && j <= A.length);
    for (; j > i; j -= LSBIT(j)) sum += A[j - 1];
    for (; i > j; i -= LSBIT(i)) sum -= A[i - 1];
    return sum;
  }
}

// Your NumArray object will be instantiated and called as such:
// NumArray obj = new NumArray(nums);
// obj.update(index,val);
// int param_2 = obj.sumRange(left,right);
