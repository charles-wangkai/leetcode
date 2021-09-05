import java.util.stream.Collectors;

class Solution {
  public String orderlyQueue(String s, int k) {
    if (k >= 2) {
      return s.chars()
          .sorted()
          .mapToObj(ch -> String.valueOf((char) ch))
          .collect(Collectors.joining());
    }

    String result = s;
    for (int i = 1; i < s.length(); ++i) {
      String rotated = s.substring(i) + s.substring(0, i);
      if (rotated.compareTo(result) < 0) {
        result = rotated;
      }
    }

    return result;
  }
}
