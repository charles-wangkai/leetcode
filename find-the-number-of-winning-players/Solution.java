import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int winningPlayerCount(int n, int[][] pick) {
    @SuppressWarnings("unchecked")
    Map<Integer, Integer>[] colorToCountMaps = new Map[n];
    for (int i = 0; i < colorToCountMaps.length; ++i) {
      colorToCountMaps[i] = new HashMap<>();
    }
    for (int[] p : pick) {
      colorToCountMaps[p[0]].put(p[1], colorToCountMaps[p[0]].getOrDefault(p[1], 0) + 1);
    }

    return (int)
        IntStream.range(0, colorToCountMaps.length)
            .filter(i -> colorToCountMaps[i].values().stream().anyMatch(count -> count > i))
            .count();
  }
}