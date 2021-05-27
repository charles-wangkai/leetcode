import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public int maxProduct(String[] words) {
    Map<Integer, Integer> keyToLength =
        Arrays.stream(words)
            .collect(Collectors.toMap(this::generateKey, String::length, Math::max));

    int result = 0;
    for (int key1 : keyToLength.keySet()) {
      for (int key2 : keyToLength.keySet()) {
        if ((key1 & key2) == 0) {
          result = Math.max(result, keyToLength.get(key1) * keyToLength.get(key2));
        }
      }
    }

    return result;
  }

  int generateKey(String word) {
    return word.chars().map(ch -> 1 << (ch - 'a')).reduce(0, (x, y) -> x | y);
  }
}
