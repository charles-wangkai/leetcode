import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int numMagicSquaresInside(int[][] grid) {
    int result = 0;
    for (int beginR = 0; beginR < grid.length - 2; ++beginR) {
      for (int beginC = 0; beginC < grid[beginR].length - 2; ++beginC) {
        if (isMagicSquare(grid, beginR, beginC)) {
          ++result;
        }
      }
    }

    return result;
  }

  boolean isMagicSquare(int[][] grid, int beginR, int beginC) {
    int[] values =
        IntStream.range(beginR, beginR + 3)
            .flatMap(r -> IntStream.range(beginC, beginC + 3).map(c -> grid[r][c]))
            .toArray();
    int sum = IntStream.range(0, 3).map(i -> values[i]).sum();

    return Arrays.stream(values).distinct().count() == 9
        && Arrays.stream(values).min().getAsInt() == 1
        && Arrays.stream(values).max().getAsInt() == 9
        && IntStream.range(3, 6).map(i -> values[i]).sum() == sum
        && IntStream.range(6, 9).map(i -> values[i]).sum() == sum
        && IntStream.range(0, 3).map(i -> values[i * 3]).sum() == sum
        && IntStream.range(0, 3).map(i -> values[i * 3 + 1]).sum() == sum
        && IntStream.range(0, 3).map(i -> values[i * 3 + 2]).sum() == sum
        && values[0] + values[4] + values[8] == sum
        && values[2] + values[4] + values[6] == sum;
  }
}
