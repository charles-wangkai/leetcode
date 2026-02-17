import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;

class Solution {
  static final int BIT_NUM = 15;

  public int maxXor(int[] nums, int k) {
    @SuppressWarnings("unchecked")
    List<Integer>[][] prefaceIndexLists = new List[BIT_NUM][1 << BIT_NUM];

    int xor = 0;
    for (int i = 0; i < nums.length; ++i) {
      xor ^= nums[i];
      for (int b = 0; b < BIT_NUM; ++b) {
        int preface = xor >> b;

        if (prefaceIndexLists[b][preface] == null) {
          prefaceIndexLists[b][preface] = new ArrayList<>();
        }
        prefaceIndexLists[b][preface].add(i);
      }
    }

    int[][] minSparseTable = buildSparseTable(nums, Math::min);
    int[][] maxSparseTable = buildSparseTable(nums, Math::max);

    int result = 0;
    int prefixXor = 0;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      int endIndex = findEndIndex(nums, k, minSparseTable, maxSparseTable, beginIndex);

      int maxXor = 0;
      for (int b = BIT_NUM - 1; b >= 0; --b) {
        int target = maxXor + (1 << b);
        if (check(prefaceIndexLists[b][(target ^ prefixXor) >> b], beginIndex, endIndex)) {
          maxXor = target;
        }
      }

      result = Math.max(result, maxXor);

      prefixXor ^= nums[beginIndex];
    }

    return result;
  }

  boolean check(List<Integer> prefaceIndexList, int beginIndex, int endIndex) {
    if (prefaceIndexList == null) {
      return false;
    }

    int left = Collections.binarySearch(prefaceIndexList, beginIndex);
    if (left < 0) {
      left = -1 - left;
    }

    int right = Collections.binarySearch(prefaceIndexList, endIndex);
    if (right < 0) {
      right = -2 - right;
    }

    return left <= right;
  }

  int findEndIndex(
      int[] nums, int k, int[][] minSparseTable, int[][] maxSparseTable, int beginIndex) {
    int result = -1;
    int lower = beginIndex;
    int upper = nums.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (computeRangeValue(maxSparseTable, beginIndex, middle, Math::max)
              - computeRangeValue(minSparseTable, beginIndex, middle, Math::min)
          <= k) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  int computeRangeValue(
      int[][] sparseTable, int beginIndex, int endIndex, BinaryOperator<Integer> binaryOperator) {
    int exponent = computeExponent(endIndex - beginIndex + 1);

    return binaryOperator.apply(
        sparseTable[beginIndex][exponent], sparseTable[endIndex - (1 << exponent) + 1][exponent]);
  }

  int[][] buildSparseTable(int[] nums, BinaryOperator<Integer> binaryOperator) {
    int[][] result = new int[nums.length][computeExponent(nums.length) + 1];
    for (int i = 0; i < result.length; ++i) {
      result[i][0] = nums[i];
    }
    for (int exponent = 1; exponent < result[0].length; ++exponent) {
      for (int i = 0; i + (1 << exponent) <= result.length; ++i) {
        result[i][exponent] =
            binaryOperator.apply(
                result[i][exponent - 1], result[i + (1 << (exponent - 1))][exponent - 1]);
      }
    }

    return result;
  }

  int computeExponent(int x) {
    return 31 - Integer.numberOfLeadingZeros(x);
  }
}