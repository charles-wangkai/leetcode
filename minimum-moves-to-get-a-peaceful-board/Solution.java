import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minMoves(int[][] rooks) {
    return computeMoveNum(Arrays.stream(rooks).mapToInt(rook -> rook[0]).toArray())
        + computeMoveNum(Arrays.stream(rooks).mapToInt(rook -> rook[1]).toArray());
  }

  int computeMoveNum(int[] positions) {
    Arrays.sort(positions);

    return IntStream.range(0, positions.length).map(i -> Math.abs(positions[i] - i)).sum();
  }
}