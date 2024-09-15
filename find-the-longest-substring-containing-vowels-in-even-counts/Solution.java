import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u'};
  static final Map<Character, Integer> VOWEL_TO_MASK =
      IntStream.range(0, VOWELS.length)
          .boxed()
          .collect(Collectors.toMap(i -> VOWELS[i], i -> 1 << i));

  public int findTheLongestSubstring(String s) {
    Map<Integer, Integer> maskToMinLength = new HashMap<>();
    maskToMinLength.put(0, 0);

    int result = 0;
    int mask = 0;
    for (int i = 0; i < s.length(); ++i) {
      if (VOWEL_TO_MASK.containsKey(s.charAt(i))) {
        mask ^= VOWEL_TO_MASK.get(s.charAt(i));
      }

      if (maskToMinLength.containsKey(mask)) {
        result = Math.max(result, i + 1 - maskToMinLength.get(mask));
      } else {
        maskToMinLength.put(mask, i + 1);
      }
    }

    return result;
  }
}