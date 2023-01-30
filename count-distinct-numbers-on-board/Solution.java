import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public int distinctIntegers(int n) {
    Set<Integer> values = new HashSet<>();
    values.add(n);

    while (true) {
      List<Integer> added =
          IntStream.rangeClosed(1, n)
              .filter(i -> !values.contains(i) && values.stream().anyMatch(value -> value % i == 1))
              .boxed()
              .toList();
      if (added.isEmpty()) {
        break;
      }

      values.addAll(added);
    }

    return values.size();
  }
}
