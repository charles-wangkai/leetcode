import java.util.Arrays;

class Solution {
  public long maxRunTime(int n, int[] batteries) {
    long result = 0;
    long lower = 1;
    long upper = Arrays.stream(batteries).asLongStream().sum();
    while (lower <= upper) {
      long middle = (lower + upper) / 2;
      if (check(n, batteries, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int n, int[] batteries, long time) {
    return Arrays.stream(batteries).mapToLong(b -> Math.min(time, b)).sum() >= n * time;
  }
}