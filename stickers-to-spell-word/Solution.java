import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Solution {
  public int minStickers(String[] stickers, String target) {
    Map<Character, Integer> targetLetter2count = convert(target, null);
    Set<Character> targetLetters = targetLetter2count.keySet();

    List<Map<Character, Integer>> stickersLetter2count = new ArrayList<>();
    for (int i = 0; i < stickers.length; i++) {
      Map<Character, Integer> letter2count = convert(stickers[i], targetLetters);

      boolean possible = true;
      for (int j = 0; j < stickersLetter2count.size(); j++) {
        Map<Character, Integer> stickerLetter2count = stickersLetter2count.get(j);

        if (isBetter(stickerLetter2count, letter2count)) {
          possible = false;
          break;
        }
      }
      if (!possible) {
        continue;
      }

      int j = 0;
      while (j < stickersLetter2count.size()) {
        Map<Character, Integer> stickerLetter2count = stickersLetter2count.get(j);

        if (isBetter(letter2count, stickerLetter2count)) {
          stickersLetter2count.remove(j);
        } else {
          ++j;
        }
      }

      stickersLetter2count.add(letter2count);
    }
    Set<Character> stickersLetters = new HashSet<>();
    for (Map<Character, Integer> stickerLetter2count : stickersLetter2count) {
      stickersLetters.addAll(stickerLetter2count.keySet());
    }

    if (stickersLetters.size() < targetLetters.size()) {
      return -1;
    }

    Set<Map<Character, Integer>> letter2countSet = Collections.singleton(new HashMap<>());
    for (int step = 1; ; step++) {
      Set<Map<Character, Integer>> nextLetter2countSet = new HashSet<>();

      for (Map<Character, Integer> letter2count : letter2countSet) {
        for (Map<Character, Integer> stickerLetter2count : stickersLetter2count) {
          Map<Character, Integer> nextLetter2count =
              add(letter2count, stickerLetter2count, targetLetter2count);

          if (nextLetter2count.equals(targetLetter2count)) {
            return step;
          }

          nextLetter2countSet.add(nextLetter2count);
        }
      }

      letter2countSet = nextLetter2countSet;
    }
  }

  boolean isBetter(
      Map<Character, Integer> letter2countFrom, Map<Character, Integer> letter2countTo) {
    for (char letter : letter2countTo.keySet()) {
      if (!letter2countFrom.containsKey(letter)
          || letter2countFrom.get(letter) < letter2countTo.get(letter)) {
        return false;
      }
    }
    return true;
  }

  Map<Character, Integer> add(
      Map<Character, Integer> letter2count,
      Map<Character, Integer> stickerLetter2count,
      Map<Character, Integer> targetLetter2count) {
    Map<Character, Integer> result = new HashMap<>(letter2count);
    for (Entry<Character, Integer> entry : stickerLetter2count.entrySet()) {
      char letter = entry.getKey();
      int count = entry.getValue();

      result.put(
          letter, Math.min(result.getOrDefault(letter, 0) + count, targetLetter2count.get(letter)));
    }
    return result;
  }

  Map<Character, Integer> convert(String s, Set<Character> scope) {
    Map<Character, Integer> letter2count = new HashMap<>();
    for (char letter : s.toCharArray()) {
      if (scope != null && !scope.contains(letter)) {
        continue;
      }

      letter2count.put(letter, letter2count.getOrDefault(letter, 0) + 1);
    }
    return letter2count;
  }
}
