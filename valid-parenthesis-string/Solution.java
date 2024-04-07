import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
  public boolean checkValidString(String s) {
    Set<Integer> depths = Set.of(0);
    for (char c : s.toCharArray()) {
      if (c == '(') {
        depths = shift(depths, 1);
      } else if (c == ')') {
        depths = shift(depths, -1);
      } else {
        depths = union(depths, union(shift(depths, 1), shift(depths, -1)));
      }
    }

    return depths.contains(0);
  }

  Set<Integer> shift(Set<Integer> depths, int delta) {
    return depths.stream()
        .map(depth -> depth + delta)
        .filter(x -> x >= 0)
        .collect(Collectors.toSet());
  }

  Set<Integer> union(Set<Integer> s1, Set<Integer> s2) {
    return Stream.concat(s1.stream(), s2.stream()).collect(Collectors.toSet());
  }
}
