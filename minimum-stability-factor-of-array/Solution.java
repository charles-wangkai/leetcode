// https://leetcode.com/problems/minimum-stability-factor-of-array/solutions/6924155/binary-search-sparse-table-two-pointers-explained-easy-to-understand-clean-code/

class Solution {
  public int minStable(int[] nums, int maxC) {
    int[][] sparseTable = buildSparseTable(nums);

    int result = -1;
    int lower = 0;
    int upper = nums.length;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, maxC, sparseTable, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  int[][] buildSparseTable(int[] nums) {
    int[][] result = new int[nums.length][computeExponent(nums.length) + 1];
    for (int i = 0; i < result.length; ++i) {
      result[i][0] = nums[i];
    }
    for (int exponent = 1; exponent < result[0].length; ++exponent) {
      for (int i = 0; i + (1 << exponent) <= result.length; ++i) {
        result[i][exponent] =
            gcd(result[i][exponent - 1], result[i + (1 << (exponent - 1))][exponent - 1]);
      }
    }

    return result;
  }

  int computeExponent(int x) {
    return 31 - Integer.numberOfLeadingZeros(x);
  }

  boolean check(int[] nums, int maxC, int[][] sparseTable, int lengthLimit) {
    int changeCount = 0;
    int beginIndex = 0;
    for (int endIndex = 0; endIndex < nums.length; ++endIndex) {
      int g = computeRangeGcd(sparseTable, beginIndex, endIndex);
      while (beginIndex <= endIndex && g == 1) {
        ++beginIndex;
        g = computeRangeGcd(sparseTable, beginIndex, endIndex);
      }

      if (endIndex - beginIndex + 1 == lengthLimit + 1) {
        ++changeCount;
        beginIndex = endIndex + 1;
      }
    }

    return changeCount <= maxC;
  }

  int computeRangeGcd(int[][] sparseTable, int beginIndex, int endIndex) {
    if (beginIndex > endIndex) {
      return -1;
    }

    int exponent = computeExponent(endIndex - beginIndex + 1);

    return gcd(
        sparseTable[beginIndex][exponent], sparseTable[endIndex - (1 << exponent) + 1][exponent]);
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
