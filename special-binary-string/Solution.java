import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public String makeLargestSpecial(String s) {
    List<String> parts = new ArrayList<>();
    int diff = 0;
    int beginIndex = 0;
    for (int i = 0; i < s.length(); ++i) {
      diff += (s.charAt(i) == '1') ? 1 : -1;

      if (diff == 0) {
        parts.add("1%s0".formatted(makeLargestSpecial(s.substring(beginIndex + 1, i))));
        beginIndex = i + 1;
      }
    }

    return parts.stream().sorted(Comparator.reverseOrder()).collect(Collectors.joining());
  }
}
