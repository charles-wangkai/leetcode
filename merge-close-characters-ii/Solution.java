import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public String mergeCharacters(String s, int k) {
    List<Character> result = new ArrayList<>();
    Set<Character> seen = new HashSet<>();
    for (char c : s.toCharArray()) {
      if (!seen.contains(c)) {
        result.add(c);

        seen.add(c);
        if (seen.size() == k + 1) {
          seen.remove(result.get(result.size() - k - 1));
        }
      }
    }

    return result.stream().map(String::valueOf).collect(Collectors.joining());
  }
}