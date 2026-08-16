import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long elevatorRequests(int n, int start, int[][] requests) {
    int requestNum = requests.length;

    long[][] dp = new long[1 << requestNum][requestNum];
    for (int i = 0; i < dp.length; ++i) {
      Arrays.fill(dp[i], Long.MAX_VALUE);
    }
    for (int i = 0; i < requests.length; ++i) {
      dp[1 << i][i] = computeTime(0, start, requests[i][0], requests[i][1]);
    }

    int[] sortedMasks =
        IntStream.range(1, (1 << requestNum) - 1)
            .boxed()
            .sorted(Comparator.comparing(Integer::bitCount))
            .mapToInt(Integer::intValue)
            .toArray();
    for (int mask : sortedMasks) {
      for (int curr = 0; curr < requests.length; ++curr) {
        if (((mask >> curr) & 1) == 1) {
          for (int next = 0; next < requests.length; ++next) {
            if (((mask >> next) & 1) == 0) {
              dp[mask + (1 << next)][next] =
                  Math.min(
                      dp[mask + (1 << next)][next],
                      computeTime(
                          dp[mask][curr], requests[curr][1], requests[next][0], requests[next][1]));
            }
          }
        }
      }
    }

    return Arrays.stream(dp[dp.length - 1]).min().getAsLong();
  }

  long computeTime(long prevTime, int prevFloor, int arrival, int floor) {
    return Math.max(arrival, prevTime + Math.abs(floor - prevFloor));
  }
}