import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String lastNonEmptyString(String s) {
    int[] counts = new int[s.length()];
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (int i = 0; i < counts.length; ++i) {
      char letter = s.charAt(i);
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
      counts[i] = letterToCount.get(letter);
    }

    int maxCount = Arrays.stream(counts).max().getAsInt();

    return IntStream.range(0, counts.length)
        .filter(i -> counts[i] == maxCount)
        .mapToObj(s::charAt)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}