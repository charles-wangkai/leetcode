import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  Map<State, Integer> cache;

  public int stoneGameII(int[] piles) {
    cache = new HashMap<>();

    return computeMaxStoneNumForFirstPlayer(piles, 0, 1);
  }

  int computeMaxStoneNumForFirstPlayer(int[] piles, int beginIndex, int M) {
    State state = new State(beginIndex, M);
    if (!cache.containsKey(state)) {
      int result = 0;
      int total = IntStream.range(beginIndex, piles.length).map(i -> piles[i]).sum();
      for (int X = 1; X <= 2 * M && beginIndex + X <= piles.length; ++X) {
        result =
            Math.max(
                result,
                total - computeMaxStoneNumForFirstPlayer(piles, beginIndex + X, Math.max(M, X)));
      }

      cache.put(state, result);
    }

    return cache.get(state);
  }
}

record State(int beginIndex, int M) {}
