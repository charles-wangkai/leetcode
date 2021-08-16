class NumArray {
  int[] prefixSums;

  public NumArray(int[] nums) {
    prefixSums = new int[nums.length];
    for (int i = 0; i < prefixSums.length; ++i) {
      prefixSums[i] = ((i == 0) ? 0 : prefixSums[i - 1]) + nums[i];
    }
  }

  public int sumRange(int left, int right) {
    return prefixSums[right] - ((left == 0) ? 0 : prefixSums[left - 1]);
  }
}

// Your NumArray object will be instantiated and called as such:
// NumArray obj = new NumArray(nums);
// int param_1 = obj.sumRange(left,right);
