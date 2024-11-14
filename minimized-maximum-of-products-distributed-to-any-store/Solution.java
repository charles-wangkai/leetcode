import java.util.Arrays;

class Solution {
  public int minimizedMaximum(int n, int[] quantities) {
    int result = -1;
    int lower = 1;
    int upper = Arrays.stream(quantities).max().getAsInt();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(n, quantities, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int n, int[] quantities, int maxProductNum) {
    return Arrays.stream(quantities)
            .map(quantity -> (quantity + maxProductNum - 1) / maxProductNum)
            .asLongStream()
            .sum()
        <= n;
  }
}
