import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public String evaluate(String s, List<List<String>> knowledge) {
    Map<String, String> knowledgeMap =
        knowledge.stream().collect(Collectors.toMap(e -> e.get(0), e -> e.get(1)));

    StringBuilder result = new StringBuilder();
    int index = 0;
    while (index != s.length()) {
      char c = s.charAt(index);
      if (c == '(') {
        int endIndex = s.indexOf(')', index);
        result.append(knowledgeMap.getOrDefault(s.substring(index + 1, endIndex), "?"));

        index = endIndex + 1;
      } else {
        result.append(c);
        ++index;
      }
    }

    return result.toString();
  }
}
