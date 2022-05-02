import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int minimumCardPickup(int[] cards) {
    Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
    for (int i = 0; i < cards.length; ++i) {
      valueToIndices.putIfAbsent(cards[i], new ArrayList<>());
      valueToIndices.get(cards[i]).add(i);
    }

    return valueToIndices.values().stream()
        .filter(indices -> indices.size() != 1)
        .mapToInt(
            indices ->
                IntStream.range(0, indices.size() - 1)
                    .map(i -> indices.get(i + 1) - indices.get(i) + 1)
                    .min()
                    .getAsInt())
        .min()
        .orElse(-1);
  }
}