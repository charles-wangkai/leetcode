import java.util.Arrays;

class Solution {
  public int maxLength(int[] ribbons, int k) {
    int result = 0;
    int lower = 1;
    int upper = Arrays.stream(ribbons).max().getAsInt();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(ribbons, k, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[] ribbons, int k, int length) {
    return Arrays.stream(ribbons).map(r -> r / length).asLongStream().sum() >= k;
  }
}