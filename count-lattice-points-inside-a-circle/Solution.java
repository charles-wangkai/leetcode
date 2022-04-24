import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int countLatticePoints(int[][] circles) {
    return IntStream.rangeClosed(0, 200)
        .map(
            x ->
                (int)
                    IntStream.rangeClosed(0, 200)
                        .filter(y -> Arrays.stream(circles).anyMatch(circle -> isIn(circle, x, y)))
                        .count())
        .sum();
  }

  boolean isIn(int[] circle, int x, int y) {
    return (circle[0] - x) * (circle[0] - x) + (circle[1] - y) * (circle[1] - y)
        <= circle[2] * circle[2];
  }
}