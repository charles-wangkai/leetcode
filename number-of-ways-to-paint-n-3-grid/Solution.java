import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final char[] COLORS = {'R', 'Y', 'G'};
  static final int MODULUS = 1_000_000_007;

  public int numOfWays(int n) {
    List<String> rows = new ArrayList<>();
    for (char c1 : COLORS) {
      for (char c2 : COLORS) {
        if (c2 != c1) {
          for (char c3 : COLORS) {
            if (c3 != c2) {
              rows.add(String.format("%c%c%c", c1, c2, c3));
            }
          }
        }
      }
    }

    Map<String, Integer> dp =
        rows.stream().collect(Collectors.toMap(Function.identity(), row -> 1));
    for (int i = 0; i < n - 1; ++i) {
      Map<String, Integer> nextDp = new HashMap<>();
      for (String row : rows) {
        int wayNum = 0;
        for (String lastRow : rows) {
          if (check(lastRow, row)) {
            wayNum = addMod(wayNum, dp.get(lastRow));
          }
        }

        nextDp.put(row, wayNum);
      }

      dp = nextDp;
    }

    return dp.values().stream().mapToInt(Integer::intValue).reduce(this::addMod).getAsInt();
  }

  boolean check(String row1, String row2) {
    return IntStream.range(0, row1.length()).allMatch(i -> row1.charAt(i) != row2.charAt(i));
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}