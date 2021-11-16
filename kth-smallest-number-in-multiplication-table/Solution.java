import java.util.stream.IntStream;

class Solution {
  public int findKthNumber(int m, int n, int k) {
    int result = -1;
    int lower = 1;
    int upper = m * n;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (computeNotGreaterNum(m, n, middle) >= k) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  int computeNotGreaterNum(int m, int n, int value) {
    return IntStream.rangeClosed(1, m).map(i -> Math.min(n, value / i)).sum();
  }
}
