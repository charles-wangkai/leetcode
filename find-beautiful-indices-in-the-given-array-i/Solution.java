import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> beautifulIndices(String s, String a, String b, int k) {
    NavigableSet<Integer> bIndices =
        new TreeSet<>(
            IntStream.rangeClosed(0, s.length() - b.length())
                .filter(i -> s.substring(i, i + b.length()).equals(b))
                .boxed()
                .toList());

    return IntStream.rangeClosed(0, s.length() - a.length())
        .filter(
            i -> {
              if (!s.substring(i, i + a.length()).equals(a)) {
                return false;
              }

              Integer lower = bIndices.lower(i);
              if (lower != null && i - lower <= k) {
                return true;
              }

              Integer ceiling = bIndices.ceiling(i);

              return ceiling != null && ceiling - i <= k;
            })
        .boxed()
        .toList();
  }
}