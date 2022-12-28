import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int visibleMountains(int[][] peaks) {
    Range[] ranges =
        Arrays.stream(peaks)
            .map(peak -> new Range(peak[0] - peak[1], peak[0] + peak[1]))
            .sorted(
                Comparator.comparing(Range::begin)
                    .thenComparing(Comparator.comparing(Range::end).reversed()))
            .toArray(Range[]::new);

    boolean[] visibles = new boolean[ranges.length];
    visibles[0] = true;
    int maxEnd = ranges[0].end();
    for (int i = 1; i < visibles.length; ++i) {
      if (ranges[i].end() > maxEnd) {
        visibles[i] = true;
        maxEnd = ranges[i].end();
      } else if (ranges[i].end() == maxEnd && ranges[i].begin() == ranges[i - 1].begin()) {
        visibles[i - 1] = false;
      }
    }

    return (int) IntStream.range(0, visibles.length).filter(i -> visibles[i]).count();
  }
}

record Range(int begin, int end) {}
