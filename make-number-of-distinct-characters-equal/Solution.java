import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public boolean isItPossible(String word1, String word2) {
    Map<Character, Integer> letterToCount1 = buildLetterToCount(word1);
    Map<Character, Integer> letterToCount2 = buildLetterToCount(word2);

    for (char letter1 : List.copyOf(letterToCount1.keySet())) {
      for (char letter2 : List.copyOf(letterToCount2.keySet())) {
        update(letterToCount1, letter1, -1);
        update(letterToCount1, letter2, 1);
        update(letterToCount2, letter2, -1);
        update(letterToCount2, letter1, 1);

        if (letterToCount1.size() == letterToCount2.size()) {
          return true;
        }

        update(letterToCount1, letter2, -1);
        update(letterToCount1, letter1, 1);
        update(letterToCount2, letter1, -1);
        update(letterToCount2, letter2, 1);
      }
    }

    return false;
  }

  void update(Map<Character, Integer> letterToCount, char letter, int delta) {
    letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + delta);
    letterToCount.remove(letter, 0);
  }

  Map<Character, Integer> buildLetterToCount(String word) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : word.toCharArray()) {
      update(letterToCount, letter, 1);
    }

    return letterToCount;
  }
}
