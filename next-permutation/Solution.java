class Solution {
  public void nextPermutation(int[] nums) {
    int startIndex = nums.length - 2;
    while (startIndex >= 0 && nums[startIndex] >= nums[startIndex + 1]) {
      --startIndex;
    }

    if (startIndex == -1) {
      reverse(nums, 0, nums.length - 1);

      return;
    }

    for (int i = nums.length - 1; ; --i) {
      if (nums[i] > nums[startIndex]) {
        swap(nums, i, startIndex);
        reverse(nums, startIndex + 1, nums.length - 1);

        return;
      }
    }
  }

  void reverse(int[] a, int beginIndex, int endIndex) {
    for (int i = beginIndex, j = endIndex; i < j; ++i, --j) {
      swap(a, i, j);
    }
  }

  void swap(int a[], int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }
}
