import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int areaOfMaxDiagonal(int[][] dimensions) {
    return Arrays.stream(dimensions)
        .max(
            Comparator.<int[], Integer>comparing(
                    dimension -> dimension[0] * dimension[0] + dimension[1] * dimension[1])
                .thenComparing(dimension -> dimension[0] * dimension[1]))
        .map(dimension -> dimension[0] * dimension[1])
        .get();
  }
}