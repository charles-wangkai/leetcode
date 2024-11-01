import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public String makeFancyString(String s) {
    List<Character> result = new ArrayList<>();
    for (char c : s.toCharArray()) {
      if (result.size() <= 1 || c != result.get(result.size() - 2) || c != result.getLast()) {
        result.add(c);
      }
    }

    return result.stream().map(String::valueOf).collect(Collectors.joining());
  }
}
