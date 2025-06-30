import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
  public List<String> partitionString(String s) {
    List<String> result = new ArrayList<>();
    Set<String> seen = new HashSet<>();
    StringBuilder current = new StringBuilder();
    for (char c : s.toCharArray()) {
      current.append(c);

      String currentStr = current.toString();
      if (!seen.contains(currentStr)) {
        result.add(currentStr);
        seen.add(currentStr);
        current.setLength(0);
      }
    }

    return result;
  }
}