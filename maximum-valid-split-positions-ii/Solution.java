import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

class Solution {
  public int maxValidSplits(int[] nums) {
    SparseTable sparseTable = new SparseTable(nums, this::gcd);

    return IntStream.range(-1, nums.length)
        .map(removedIndex -> computeScore(sparseTable, removedIndex))
        .max()
        .getAsInt();
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }

  int computeScore(SparseTable sparseTable, int removedIndex) {
    int minLength = findMinLength(sparseTable, removedIndex);
    if (minLength == -1) {
      return 0;
    }

    int maxLength = findMaxLength(sparseTable, removedIndex);

    return maxLength - minLength + 1;
  }

  int findMinLength(SparseTable sparseTable, int removedIndex) {
    int n = sparseTable.st.length;

    int length = n - ((removedIndex == -1) ? 0 : 1);

    int leftLength = -1;
    int lower = 1;
    int upper = length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (computeLeftGcd(sparseTable, removedIndex, middle)
          <= computeRightGcd(sparseTable, removedIndex, length - middle)) {
        leftLength = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return (leftLength == -1
            || computeLeftGcd(sparseTable, removedIndex, leftLength)
                != computeRightGcd(sparseTable, removedIndex, length - leftLength))
        ? -1
        : leftLength;
  }

  int findMaxLength(SparseTable sparseTable, int removedIndex) {
    int n = sparseTable.st.length;

    int length = n - ((removedIndex == -1) ? 0 : 1);

    int leftLength = -1;
    int lower = 1;
    int upper = length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (computeLeftGcd(sparseTable, removedIndex, middle)
          >= computeRightGcd(sparseTable, removedIndex, length - middle)) {
        leftLength = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return leftLength;
  }

  int computeLeftGcd(SparseTable sparseTable, int removedIndex, int leftLength) {
    if (removedIndex == -1 || removedIndex >= leftLength) {
      return sparseTable.query(0, leftLength - 1);
    }
    if (removedIndex == 0) {
      return sparseTable.query(1, leftLength);
    }

    return gcd(
        sparseTable.query(0, removedIndex - 1), sparseTable.query(removedIndex + 1, leftLength));
  }

  int computeRightGcd(SparseTable sparseTable, int removedIndex, int rightLength) {
    int n = sparseTable.st.length;

    if (removedIndex < n - rightLength) {
      return sparseTable.query(n - rightLength, n - 1);
    }
    if (removedIndex == n - 1) {
      return sparseTable.query(n - rightLength - 1, n - 2);
    }

    return gcd(
        sparseTable.query(n - rightLength - 1, removedIndex - 1),
        sparseTable.query(removedIndex + 1, n - 1));
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
