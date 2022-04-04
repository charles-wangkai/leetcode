import java.util.Arrays;

class Solution {
  public int maximumCandies(int[] candies, long k) {
    int result = 0;
    int lower = 1;
    int upper = Arrays.stream(candies).max().getAsInt();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(candies, k, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[] candies, long k, int unit) {
    return Arrays.stream(candies).map(candy -> candy / unit).asLongStream().sum() >= k;
  }
}