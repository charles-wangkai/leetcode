import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int firstUniqChar(String s) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    return IntStream.range(0, s.length())
        .filter(i -> letterToCount.get(s.charAt(i)) == 1)
        .findFirst()
        .orElse(-1);
  }
}
