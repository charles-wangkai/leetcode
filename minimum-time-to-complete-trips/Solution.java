import java.util.Arrays;

class Solution {
  public long minimumTime(int[] time, int totalTrips) {
    long result = -1;
    long lower = 0;
    long upper = (long) Arrays.stream(time).min().getAsInt() * totalTrips;
    while (lower <= upper) {
      long middle = (lower + upper) / 2;
      if (check(time, totalTrips, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] time, int totalTrips, long totalTime) {
    long tripNum = 0;
    for (int t : time) {
      tripNum += totalTime / t;
      if (tripNum >= totalTrips) {
        return true;
      }
    }

    return false;
  }
}