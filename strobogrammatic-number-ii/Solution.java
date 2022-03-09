import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final int[] LEFTS = {0, 1, 6, 8, 9};
  static final int[] RIGHTS = {0, 1, 9, 8, 6};

  public List<String> findStrobogrammatic(int n) {
    return search(n, false);
  }

  public List<String> search(int n, boolean allowLeadingZero) {
    if (n == 0) {
      return List.of("");
    }
    if (n == 1) {
      return IntStream.range(0, LEFTS.length)
          .filter(i -> LEFTS[i] == RIGHTS[i])
          .map(i -> LEFTS[i])
          .mapToObj(String::valueOf)
          .collect(Collectors.toList());
    }

    return search(n - 2, true).stream()
        .flatMap(
            subResult ->
                IntStream.range(0, LEFTS.length)
                    .filter(i -> allowLeadingZero || LEFTS[i] != 0)
                    .mapToObj(i -> String.format("%d%s%d", LEFTS[i], subResult, RIGHTS[i])))
        .collect(Collectors.toList());
  }
}
