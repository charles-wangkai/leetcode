import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public boolean isPossibleToRearrange(String s, String t, int k) {
    return convert(s, k).equals(convert(t, k));
  }

  String convert(String str, int k) {
    return IntStream.range(0, k)
        .mapToObj(i -> str.substring(i * (str.length() / k), (i + 1) * (str.length() / k)))
        .sorted()
        .collect(Collectors.joining());
  }
}