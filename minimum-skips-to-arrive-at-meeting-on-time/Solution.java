import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minSkips(int[] dist, int speed, int hoursBefore) {
    Map<Integer, Long> skipNumToNumerator = Map.of(0, 0L);

    for (int i = 0; i < dist.length - 1; ++i) {
      Map<Integer, Long> nextSkipNumToNumerator = new HashMap<>();
      for (int skipNum : skipNumToNumerator.keySet()) {
        nextSkipNumToNumerator.put(
            skipNum,
            Math.min(
                nextSkipNumToNumerator.getOrDefault(skipNum, Long.MAX_VALUE),
                toNextMultiple(speed, skipNumToNumerator.get(skipNum) + dist[i])));

        nextSkipNumToNumerator.put(
            skipNum + 1,
            Math.min(
                nextSkipNumToNumerator.getOrDefault(skipNum + 1, Long.MAX_VALUE),
                skipNumToNumerator.get(skipNum) + dist[i]));
      }

      skipNumToNumerator = nextSkipNumToNumerator;
    }

    Map<Integer, Long> skipNumToNumerator_ = skipNumToNumerator;
    return skipNumToNumerator.keySet().stream()
        .filter(
            skipNum ->
                skipNumToNumerator_.get(skipNum) + dist[dist.length - 1]
                    <= (long) hoursBefore * speed)
        .mapToInt(x -> x)
        .min()
        .orElse(-1);
  }

  long toNextMultiple(int speed, long numerator) {
    return (numerator + speed - 1) / speed * speed;
  }
}
