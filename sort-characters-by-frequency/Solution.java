import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public String frequencySort(String s) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    return letterToCount.keySet().stream()
        .sorted(Comparator.comparing(letterToCount::get).reversed())
        .map(letter -> String.valueOf(letter).repeat(letterToCount.get(letter)))
        .collect(Collectors.joining());
  }
}
