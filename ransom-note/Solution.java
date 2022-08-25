import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean canConstruct(String ransomNote, String magazine) {
    Map<Character, Integer> ransomLetterToCount = buildLetterToCount(ransomNote);
    Map<Character, Integer> magazineLetterToCount = buildLetterToCount(magazine);

    return ransomLetterToCount.keySet().stream()
        .allMatch(
            letter ->
                magazineLetterToCount.getOrDefault(letter, 0) >= ransomLetterToCount.get(letter));
  }

  Map<Character, Integer> buildLetterToCount(String s) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    return letterToCount;
  }
}
