import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int partitionString(String s) {
    int result = 0;
    Set<Character> seen =
        IntStream.rangeClosed('a', 'z').mapToObj(c -> (char) (c)).collect(Collectors.toSet());
    for (char c : s.toCharArray()) {
      if (seen.contains(c)) {
        ++result;
        seen.clear();
      }
      seen.add(c);
    }

    return result;
  }
}