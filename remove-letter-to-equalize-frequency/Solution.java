import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean equalFrequency(String word) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : word.toCharArray()) {
      updateMap(letterToCount, letter, 1);
    }

    return check(
            letterToCount,
            letterToCount.keySet().stream().min(Comparator.comparing(letterToCount::get)).get())
        || check(
            letterToCount,
            letterToCount.keySet().stream().max(Comparator.comparing(letterToCount::get)).get());
  }

  static boolean check(Map<Character, Integer> letterToCount, char letter) {
    updateMap(letterToCount, letter, -1);
    if (letterToCount.values().stream().distinct().count() == 1) {
      return true;
    }

    updateMap(letterToCount, letter, 1);

    return false;
  }

  static void updateMap(Map<Character, Integer> map, char key, int delta) {
    map.put(key, map.getOrDefault(key, 0) + delta);
    map.remove(key, 0);
  }
}
