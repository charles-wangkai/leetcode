import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
  public int countMagicalPairs(String s) {
    int result = 0;
    for (int i = 0; i < s.length() - 1; ++i) {
      Map<Integer, Integer> leftDistinctNumToCount =
          buildDistinctNumToCount(new StringBuilder(s.substring(0, i + 1)).reverse().toString());
      Map<Integer, Integer> rightDistinctNumToCount = buildDistinctNumToCount(s.substring(i + 1));

      result +=
          leftDistinctNumToCount.keySet().stream()
              .mapToInt(
                  distinctNumToCount ->
                      leftDistinctNumToCount.get(distinctNumToCount)
                          * rightDistinctNumToCount.getOrDefault(distinctNumToCount, 0))
              .sum();
    }

    return result;
  }

  Map<Integer, Integer> buildDistinctNumToCount(String str) {
    Map<Integer, Integer> distinctNumToCount = new HashMap<>();
    Set<Character> seen = new HashSet<>();
    for (char letter : str.toCharArray()) {
      seen.add(letter);
      distinctNumToCount.put(seen.size(), distinctNumToCount.getOrDefault(seen.size(), 0) + 1);
    }

    return distinctNumToCount;
  }
}