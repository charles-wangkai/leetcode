import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

class Solution {
  public long countConsistent(int[] nums) {
    SparseTable sparseTable = new SparseTable(nums, (x, y) -> x & y);

    long result = 0;
    Map<Integer, Integer> valueToLastIndex = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      result +=
          (i
                  - findLeftIndex(
                      nums, sparseTable, i, valueToLastIndex.getOrDefault(nums[i], -1) + 1)
                  + 1L)
              * (findRightIndex(nums, sparseTable, i) - i + 1);

      valueToLastIndex.put(nums[i], i);
    }

    return result;
  }

  int findLeftIndex(int[] nums, SparseTable sparseTable, int index, int lower) {
    int result = -1;
    int upper = index;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if ((sparseTable.query(middle, index) & nums[index]) == nums[index]) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  int findRightIndex(int[] nums, SparseTable sparseTable, int index) {
    int result = -1;
    int lower = index;
    int upper = nums.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if ((sparseTable.query(index, middle) & nums[index]) == nums[index]) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}

class SparseTable {
  int[][] st;
  BinaryOperator<Integer> operator;

  SparseTable(int[] values, BinaryOperator<Integer> operator) {
    st = new int[values.length][computeExponent(values.length) + 1];
    for (int i = 0; i < st.length; ++i) {
      st[i][0] = values[i];
    }
    for (int exponent = 1; exponent < st[0].length; ++exponent) {
      for (int i = 0; i + (1 << exponent) <= st.length; ++i) {
        st[i][exponent] =
            operator.apply(st[i][exponent - 1], st[i + (1 << (exponent - 1))][exponent - 1]);
      }
    }

    this.operator = operator;
  }

  int query(int beginIndex, int endIndex) {
    int exponent = computeExponent(endIndex - beginIndex + 1);

    return operator.apply(st[beginIndex][exponent], st[endIndex - (1 << exponent) + 1][exponent]);
  }

  private int computeExponent(int x) {
    return 31 - Integer.numberOfLeadingZeros(x);
  }
}
