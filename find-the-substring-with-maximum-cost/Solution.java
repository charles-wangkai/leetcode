import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int maximumCostSubstring(String s, String chars, int[] vals) {
    Map<Character, Integer> letterToValue =
        IntStream.range(0, chars.length())
            .boxed()
            .collect(Collectors.toMap(chars::charAt, i -> vals[i]));

    int result = 0;
    int sum = 0;
    for (char letter : s.toCharArray()) {
      sum += letterToValue.getOrDefault(letter, letter - 'a' + 1);
      result = Math.max(result, sum);
      sum = Math.max(0, sum);
    }

    return result;
  }
}
