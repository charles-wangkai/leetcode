
class Solution {
  public int minOperations(int[] nums1, int[] nums2) {
    int[] counts1 = buildCounts(nums1);
    int[] counts2 = buildCounts(nums2);

    int result = Integer.MAX_VALUE;
    for (int target = 0; target <= Math.max(nums1.length, nums2.length) * 6; ++target) {
      Integer operationNum1 = computeOperationNum(counts1, target);
      Integer operationNum2 = computeOperationNum(counts2, target);
      if (operationNum1 != null && operationNum2 != null) {
        result = Math.min(result, operationNum1 + operationNum2);
      }
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }

  int[] buildCounts(int[] nums) {
    int[] counts = new int[7];
    for (int num : nums) {
      ++counts[num];
    }

    return counts;
  }

  Integer computeOperationNum(int[] counts, int target) {
    int length = 0;
    int sum = 0;
    for (int i = 1; i < counts.length; ++i) {
      length += counts[i];
      sum += i * counts[i];
    }

    int result = 0;
    if (sum < target) {
      if (length * 6 < target) {
        return null;
      }

      for (int i = 1; i <= 5; ++i) {
        int delta = 6 - i;
        int count = Math.min(counts[i], (target - sum + delta - 1) / delta);
        result += count;
        sum = Math.min(target, sum + delta * count);
      }
    } else if (sum > target) {
      if (length > target) {
        return null;
      }

      for (int i = 6; i >= 2; --i) {
        int delta = i - 1;
        int count = Math.min(counts[i], (sum - target + delta - 1) / delta);
        result += count;
        sum = Math.max(target, sum - delta * count);
      }
    }

    return result;
  }
}
