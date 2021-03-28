import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public String evaluate(String s, List<List<String>> knowledge) {
    Map<String, String> map =
        knowledge.stream().collect(Collectors.toMap(e -> e.get(0), e -> e.get(1)));

    StringBuilder result = new StringBuilder();
    int index = 0;
    while (index != s.length()) {
      char ch = s.charAt(index);
      if (ch == '(') {
        int endIndex = s.indexOf(')', index);
        result.append(map.getOrDefault(s.substring(index + 1, endIndex), "?"));

        index = endIndex + 1;
      } else {
        result.append(ch);
        ++index;
      }
    }

    return result.toString();
  }
}
