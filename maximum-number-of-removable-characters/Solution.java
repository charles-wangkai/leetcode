import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int maximumRemovals(String s, String p, int[] removable) {
    int result = -1;
    int lower = 0;
    int upper = removable.length;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(s, p, removable, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(String s, String p, int[] removable, int k) {
    Set<Integer> indices =
        IntStream.range(0, k).map(i -> removable[i]).boxed().collect(Collectors.toSet());

    String removed =
        IntStream.range(0, s.length())
            .filter(i -> !indices.contains(i))
            .mapToObj(i -> String.valueOf(s.charAt(i)))
            .collect(Collectors.joining());

    int fromIndex = 0;
    for (char ch : p.toCharArray()) {
      int index = removed.indexOf(ch, fromIndex);
      if (index == -1) {
        return false;
      }

      fromIndex = index + 1;
    }

    return true;
  }
}
