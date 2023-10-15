import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public String shortestBeautifulSubstring(String s, int k) {
    int[] oneIndices = IntStream.range(0, s.length()).filter(i -> s.charAt(i) == '1').toArray();
    if (oneIndices.length < k) {
      return "";
    }

    String[] candidates =
        IntStream.rangeClosed(0, oneIndices.length - k)
            .mapToObj(i -> s.substring(oneIndices[i], oneIndices[i + k - 1] + 1))
            .toArray(String[]::new);
    int minLength = Arrays.stream(candidates).mapToInt(String::length).min().getAsInt();

    return Arrays.stream(candidates)
        .filter(candidate -> candidate.length() == minLength)
        .min(Comparator.naturalOrder())
        .get();
  }
}
