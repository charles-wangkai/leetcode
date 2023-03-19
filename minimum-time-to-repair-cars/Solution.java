import java.util.Arrays;

class Solution {
  public long repairCars(int[] ranks, int cars) {
    long result = -1;
    long lower = 1;
    long upper = (long) Arrays.stream(ranks).min().getAsInt() * cars * cars;
    while (lower <= upper) {
      long middle = (lower + upper) / 2;
      if (check(ranks, cars, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] ranks, int cars, long time) {
    long total = 0;
    for (int rank : ranks) {
      total += sqrt(time / rank);
      if (total >= cars) {
        return true;
      }
    }

    return false;
  }

  int sqrt(long x) {
    int result = (int) Math.round(Math.sqrt(x));
    if ((long) result * result > x) {
      --result;
    }

    return result;
  }
}
