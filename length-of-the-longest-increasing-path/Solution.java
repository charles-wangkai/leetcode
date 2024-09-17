import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
  public int maxPathLength(int[][] coordinates, int k) {
    Comparator<int[]> coordinateComparator =
        Comparator.<int[], Integer>comparing(coordinate -> coordinate[0])
            .thenComparing(
                Comparator.<int[], Integer>comparing(coordinate -> coordinate[1]).reversed());

    return computeMaxIncreasingLength(
            Arrays.stream(coordinates)
                .filter(
                    coordinate ->
                        coordinate[0] < coordinates[k][0] && coordinate[1] < coordinates[k][1])
                .sorted(coordinateComparator)
                .mapToInt(coordinate -> coordinate[1])
                .toArray())
        + computeMaxIncreasingLength(
            Arrays.stream(coordinates)
                .filter(
                    coordinate ->
                        coordinate[0] > coordinates[k][0] && coordinate[1] > coordinates[k][1])
                .sorted(coordinateComparator)
                .mapToInt(coordinate -> coordinate[1])
                .toArray())
        + 1;
  }

  int computeMaxIncreasingLength(int[] values) {
    List<Integer> sequence = new ArrayList<>();
    for (int value : values) {
      if (sequence.isEmpty() || value > sequence.get(sequence.size() - 1)) {
        sequence.add(value);
      } else {
        int index = Collections.binarySearch(sequence, value);
        if (index < 0) {
          sequence.set(-index - 1, value);
        }
      }
    }

    return sequence.size();
  }
}