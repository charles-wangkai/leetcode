import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public String makeFancyString(String s) {
    List<Character> deleted = new ArrayList<>();
    for (char ch : s.toCharArray()) {
      if (deleted.size() <= 1
          || ch != deleted.get(deleted.size() - 2)
          || ch != deleted.get(deleted.size() - 1)) {
        deleted.add(ch);
      }
    }

    return deleted.stream().map(String::valueOf).collect(Collectors.joining());
  }
}
