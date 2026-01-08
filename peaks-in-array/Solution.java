import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
    boolean[] peaks = new boolean[nums.length];
    FenwickTree fenwickTree = new FenwickTree(peaks.length);
    for (int i = 1; i < peaks.length - 1; ++i) {
      if (nums[i] > Math.max(nums[i - 1], nums[i + 1])) {
        peaks[i] = true;
        fenwickTree.add(i + 1, 1);
      }
    }

    List<Integer> result = new ArrayList<>();
    for (int[] query : queries) {
      if (query[0] == 1) {
        int leftIndex = query[1];
        int rightIndex = query[2];

        result.add(computeRangeSum(fenwickTree, leftIndex + 2, rightIndex));
      } else {
        int index = query[1];
        int value = query[2];

        nums[index] = value;

        for (int i = -1; i <= 1; ++i) {
          int affected = index + i;
          if (affected >= 1 && affected <= nums.length - 2) {
            boolean peak = nums[affected] > Math.max(nums[affected - 1], nums[affected + 1]);
            if (peak != peaks[affected]) {
              if (peak) {
                fenwickTree.add(affected + 1, 1);
              } else {
                fenwickTree.add(affected + 1, -1);
              }

              peaks[affected] = peak;
            }
          }
        }
      }
    }

    return result;
  }

  int computeRangeSum(FenwickTree fenwickTree, int beginIndex, int endIndex) {
    return (beginIndex <= endIndex)
        ? (fenwickTree.computePrefixSum(endIndex) - fenwickTree.computePrefixSum(beginIndex - 1))
        : 0;
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
