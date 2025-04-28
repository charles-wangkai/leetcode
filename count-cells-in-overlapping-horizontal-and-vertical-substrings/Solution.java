import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final int BASE = 31;
  static final int MODULUS = 1_000_000_007;

  public int countCells(char[][] grid, String pattern) {
    int m = grid.length;
    int n = grid[0].length;

    int[] horizontalCovered =
        buildCovered(pattern, Arrays.stream(grid).map(String::new).collect(Collectors.joining()));
    int[] verticalCovered =
        buildCovered(
            pattern,
            IntStream.range(0, n)
                .mapToObj(
                    c ->
                        IntStream.range(0, m)
                            .mapToObj(r -> grid[r][c])
                            .map(String::valueOf)
                            .collect(Collectors.joining()))
                .collect(Collectors.joining()));

    int result = 0;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (horizontalCovered[r * n + c] != 0 && verticalCovered[c * m + r] != 0) {
          ++result;
        }
      }
    }

    return result;
  }

  int[] buildCovered(String pattern, String s) {
    int targetHash = 0;
    for (char c : pattern.toCharArray()) {
      targetHash = addMod(multiplyMod(targetHash, BASE), c - 'a');
    }

    int basePower = 1;
    for (int i = 0; i < pattern.length() - 1; ++i) {
      basePower = multiplyMod(basePower, BASE);
    }

    int hash = 0;
    for (int i = 0; i < pattern.length() - 1; ++i) {
      hash = addMod(multiplyMod(hash, BASE), s.charAt(i) - 'a');
    }

    int[] deltas = new int[s.length()];
    for (int i = pattern.length() - 1; i < s.length(); ++i) {
      hash = addMod(multiplyMod(hash, BASE), s.charAt(i) - 'a');

      if (hash == targetHash) {
        ++deltas[i - pattern.length() + 1];
        if (i != deltas.length - 1) {
          --deltas[i + 1];
        }
      }

      hash = addMod(hash, -multiplyMod(s.charAt(i - pattern.length() + 1) - 'a', basePower));
    }

    int[] result = new int[deltas.length];
    int c = 0;
    for (int i = 0; i < result.length; ++i) {
      c += deltas[i];
      result[i] = c;
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}