class Solution {
  public int findPeakElement(int[] nums) {
    return findPeakElement(nums, 0, nums.length - 1);
  }

  int findPeakElement(int[] nums, int begin, int end) {
    if (begin == end) {
      return begin;
    }

    int middle = (begin + end) / 2;
    if (isPeak(nums, middle)) {
      return middle;
    }

    if (nums[middle + 1] > nums[middle]) {
      return findPeakElement(nums, middle + 1, end);
    } else {
      return findPeakElement(nums, begin, middle - 1);
    }
  }

  boolean isPeak(int[] nums, int index) {
    return (index == 0 || nums[index] > nums[index - 1])
        && (index == nums.length - 1 || nums[index] > nums[index + 1]);
  }
}
