import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
  public boolean checkValidString(String s) {
    Set<Integer> depths = new HashSet<>();
    depths.add(0);
    for (char ch : s.toCharArray()) {
      if (ch == '(') {
        depths = shift(depths, 1);
      } else if (ch == ')') {
        depths = shift(depths, -1);
      } else if (ch == '*') {
        depths = union(depths, union(shift(depths, 1), shift(depths, -1)));
      }
    }

    return depths.contains(0);
  }

  Set<Integer> shift(Set<Integer> depths, int delta) {
    return depths.stream()
        .map(number -> number + delta)
        .filter(x -> x >= 0)
        .collect(Collectors.toSet());
  }

  Set<Integer> union(Set<Integer> s1, Set<Integer> s2) {
    return Stream.concat(s1.stream(), s2.stream()).collect(Collectors.toSet());
  }
}
