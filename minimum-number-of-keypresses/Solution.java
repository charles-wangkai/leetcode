import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int minimumKeypresses(String s) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    int[] sortedCounts =
        letterToCount.values().stream()
            .sorted(Comparator.reverseOrder())
            .mapToInt(x -> x)
            .toArray();

    return IntStream.range(0, sortedCounts.length).map(i -> (i / 9 + 1) * sortedCounts[i]).sum();
  }
}
