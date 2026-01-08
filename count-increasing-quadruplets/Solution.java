// https://en.wikipedia.org/wiki/Fenwick_tree

import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long countQuadruplets(int[] nums) {
    long result = 0;
    for (int k = 1; k < nums.length; ++k) {
      int[] rightSorted = IntStream.range(k + 1, nums.length).map(i -> nums[i]).sorted().toArray();

      FenwickTree fenwickTree = new FenwickTree(nums.length);
      for (int j = 0; j < k; ++j) {
        if (nums[j] > nums[k]) {
          result +=
              fenwickTree.computePrefixSum(nums[k] + 1) * computeRightNum(rightSorted, nums[j]);
        }

        fenwickTree.add(nums[j] + 1, 1);
      }
    }

    return result;
  }

  int computeRightNum(int[] rightSorted, int target) {
    return rightSorted.length - (-1 - Arrays.binarySearch(rightSorted, target));
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
