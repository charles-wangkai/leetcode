import java.util.OptionalInt;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

class Solution {
  public long minMoves(int[] balance) {
    OptionalInt negativeIndex =
        IntStream.range(0, balance.length).filter(i -> balance[i] < 0).findAny();
    if (negativeIndex.isEmpty()) {
      return 0;
    }

    SortedMap<Integer, Long> distanceToValueSum = new TreeMap<>();
    for (int i = 0; i < balance.length; ++i) {
      if (balance[i] >= 0) {
        int distance =
            Math.min(
                Math.abs(i - negativeIndex.getAsInt()),
                balance.length - Math.abs(i - negativeIndex.getAsInt()));

        distanceToValueSum.put(
            distance, distanceToValueSum.getOrDefault(distance, 0L) + balance[i]);
      }
    }

    int rest = -balance[negativeIndex.getAsInt()];
    if (distanceToValueSum.values().stream().mapToLong(Long::longValue).sum() < rest) {
      return -1;
    }

    long result = 0;
    for (int distance : distanceToValueSum.keySet()) {
      int num = (int) Math.min(rest, distanceToValueSum.get(distance));
      result += (long) num * distance;
      rest -= num;
    }

    return result;
  }
}