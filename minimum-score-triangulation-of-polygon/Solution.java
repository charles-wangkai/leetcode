import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int minScoreTriangulation(int[] values) {
    return search(values, new HashMap<>(), 0, values.length - 2);
  }

  int search(int[] values, Map<State, Integer> cache, int beginIndex, int endIndex) {
    if (beginIndex == endIndex) {
      return 0;
    }

    State state = new State(beginIndex, endIndex);
    if (!cache.containsKey(state)) {
      int result =
          IntStream.range(beginIndex, endIndex)
              .map(
                  i ->
                      search(values, cache, beginIndex, i)
                          + values[beginIndex] * values[i + 1] * values[endIndex + 1]
                          + search(values, cache, i + 1, endIndex))
              .min()
              .getAsInt();

      cache.put(state, result);
    }

    return cache.get(state);
  }
}

record State(int beginIndex, int endIndex) {}
