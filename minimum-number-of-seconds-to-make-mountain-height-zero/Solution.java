import java.util.Arrays;

class Solution {
  public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
    long result = -1;
    long lower = 1;
    long upper = mountainHeight * (mountainHeight + 1L) / 2 * workerTimes[0];
    while (lower <= upper) {
      long middle = lower + (upper - lower) / 2;
      if (check(mountainHeight, workerTimes, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int mountainHeight, int[] workerTimes, long time) {
    return Arrays.stream(workerTimes)
            .map(
                workerTime -> {
                  long target = time * 2 / workerTime;
                  int result = (int) Math.round(Math.sqrt(target));
                  if (result * (result + 1L) > target) {
                    --result;
                  }

                  return result;
                })
            .asLongStream()
            .sum()
        >= mountainHeight;
  }
}
