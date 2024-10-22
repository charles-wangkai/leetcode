import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<String> stringSequence(String target) {
    List<String> result = new ArrayList<>();
    StringBuilder s = new StringBuilder();
    while (s.length() != target.length()) {
      s.append('a');
      result.add(s.toString());

      while (s.charAt(s.length() - 1) != target.charAt(s.length() - 1)) {
        s.setCharAt(s.length() - 1, (char) ((s.charAt(s.length() - 1) - 'a' + 1) % 26 + 'a'));
        result.add(s.toString());
      }
    }

    return result;
  }
}