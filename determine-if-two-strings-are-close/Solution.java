import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean closeStrings(String word1, String word2) {
    Map<Character, Integer> letterToCount1 = buildletterToCount(word1);
    Map<Character, Integer> letterToCount2 = buildletterToCount(word2);

    return letterToCount1.keySet().equals(letterToCount2.keySet())
        && Arrays.equals(
            letterToCount1.values().stream().mapToInt(x -> x).sorted().toArray(),
            letterToCount2.values().stream().mapToInt(x -> x).sorted().toArray());
  }

  Map<Character, Integer> buildletterToCount(String word) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : word.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    return letterToCount;
  }
}
