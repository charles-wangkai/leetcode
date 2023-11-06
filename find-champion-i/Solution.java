import java.util.stream.IntStream;

class Solution {
  public int findChampion(int[][] grid) {
    int n = grid.length;

    return IntStream.range(0, n)
        .filter(i -> IntStream.range(0, n).filter(j -> j != i).allMatch(j -> grid[i][j] == 1))
        .findAny()
        .getAsInt();
  }
}
