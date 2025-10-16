import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public String filterCharacters(String s, int k) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    return s.chars()
        .mapToObj(c -> (char) c)
        .filter(c -> letterToCount.get(c) < k)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}