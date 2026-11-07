// https://leetcode.com/problems/elevator-requests-iv/solutions/8467870/binary-search-interval-dp-ok2-log-u-by-c-jcoz/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  static final long LIMIT = 501_000_000_000L;

  public long elevatorRequests(int n, int start, int[][] requests) {
    Map<Integer, Integer> floorToArrival = new HashMap<>();
    for (int[] request : requests) {
      int arrival = request[0];
      int floor = request[1];

      floorToArrival.put(floor, Math.max(floorToArrival.getOrDefault(floor, -1), arrival));
    }

    int[] floors = floorToArrival.keySet().stream().mapToInt(Integer::intValue).sorted().toArray();

    long result = -1;
    long lower = floorToArrival.values().stream().mapToInt(Integer::intValue).max().getAsInt();
    long upper = LIMIT;
    while (lower <= upper) {
      long middle = (lower + upper) / 2;
      if (check(start, floorToArrival, floors, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int start, Map<Integer, Integer> floorToArrival, int[] floors, long totalTime) {
    long[] leftDp = new long[floors.length];
    long[] rightDp = new long[floors.length];

    for (int length = 2; length <= floors.length; ++length) {
      int size = floors.length - length + 1;

      long[] nextLeftDp = new long[size];
      Arrays.fill(nextLeftDp, Long.MAX_VALUE);

      long[] nextRightDp = new long[size];
      Arrays.fill(nextRightDp, Long.MAX_VALUE);

      for (int left = 0; left < size; ++left) {
        int right = left + length - 1;

        long leftTime =
            Math.min(
                add(leftDp[left + 1], floors[left + 1] - floors[left]),
                add(rightDp[left + 1], floors[right] - floors[left]));
        if (leftTime <= totalTime - floorToArrival.get(floors[left])) {
          nextLeftDp[left] = leftTime;
        }

        long rightTime =
            Math.min(
                add(leftDp[left], floors[right] - floors[left]),
                add(rightDp[left], floors[right] - floors[right - 1]));
        if (rightTime <= totalTime - floorToArrival.get(floors[right])) {
          nextRightDp[left] = rightTime;
        }
      }

      leftDp = nextLeftDp;
      rightDp = nextRightDp;
    }

    return Math.min(
            add(leftDp[0], Math.abs(floors[0] - start)),
            add(rightDp[0], Math.abs(floors[floors.length - 1] - start)))
        <= totalTime;
  }

  long add(long x, long y) {
    return (x == Long.MAX_VALUE) ? Long.MAX_VALUE : (x + y);
  }
}
