// https://leetcode.com/problems/minimum-stability-factor-of-array/solutions/6924155/binary-search-sparse-table-two-pointers-explained-easy-to-understand-clean-code/

import java.util.function.BinaryOperator;

class Solution {
  public int minStable(int[] nums, int maxC) {
    SparseTable sparseTable = new SparseTable(nums, this::gcd);

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

  boolean check(int[] nums, int maxC, SparseTable sparseTable, int lengthLimit) {
    int changeCount = 0;
    int beginIndex = 0;
    for (int endIndex = 0; endIndex < nums.length; ++endIndex) {
      while (beginIndex <= endIndex && sparseTable.query(beginIndex, endIndex) == 1) {
        ++beginIndex;
      }

      if (endIndex - beginIndex + 1 == lengthLimit + 1) {
        ++changeCount;
        beginIndex = endIndex + 1;
      }
    }

    return changeCount <= maxC;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}

class SparseTable {
  int[][] st;
  BinaryOperator<Integer> binaryOperator;

  SparseTable(int[] values, BinaryOperator<Integer> binaryOperator) {
    st = new int[values.length][computeExponent(values.length) + 1];
    for (int i = 0; i < st.length; ++i) {
      st[i][0] = values[i];
    }
    for (int exponent = 1; exponent < st[0].length; ++exponent) {
      for (int i = 0; i + (1 << exponent) <= st.length; ++i) {
        st[i][exponent] =
            binaryOperator.apply(st[i][exponent - 1], st[i + (1 << (exponent - 1))][exponent - 1]);
      }
    }

    this.binaryOperator = binaryOperator;
  }

  int query(int beginIndex, int endIndex) {
    int exponent = computeExponent(endIndex - beginIndex + 1);

    return binaryOperator.apply(
        st[beginIndex][exponent], st[endIndex - (1 << exponent) + 1][exponent]);
  }

  private int computeExponent(int x) {
    return 31 - Integer.numberOfLeadingZeros(x);
  }
}
