import java.util.Arrays;

class Solution {
  public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
    return 2 * Arrays.stream(nuts).mapToInt(nut -> computeDistance(nut, tree)).sum()
        + Arrays.stream(nuts)
            .mapToInt(nut -> computeDistance(nut, squirrel) - computeDistance(nut, tree))
            .min()
            .getAsInt();
  }

  int computeDistance(int[] pos1, int[] pos2) {
    return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
  }
}
