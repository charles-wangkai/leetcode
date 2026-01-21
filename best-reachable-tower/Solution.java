import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int[] bestTower(int[][] towers, int[] center, int radius) {
    return Arrays.stream(towers)
        .filter(tower -> Math.abs(tower[0] - center[0]) + Math.abs(tower[1] - center[1]) <= radius)
        .max(
            Comparator.<int[], Integer>comparing(tower -> tower[2])
                .thenComparing(
                    Comparator.<int[], Integer>comparing(tower -> tower[0])
                        .thenComparing(tower -> tower[1])
                        .reversed()))
        .map(tower -> new int[] {tower[0], tower[1]})
        .orElse(new int[] {-1, -1});
  }
}