import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int minimumPushes(String word) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : word.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    int[] sortedCounts =
        letterToCount.values().stream()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

    return IntStream.range(0, sortedCounts.length).map(i -> (i / 8 + 1) * sortedCounts[i]).sum();
  }
}