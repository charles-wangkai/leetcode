import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  public int maxBuilding(int n, int[][] restrictions) {
    List<Restriction> restrs =
        Stream.concat(
                Stream.of(new Restriction(1, 0)),
                Arrays.stream(restrictions).map(r -> new Restriction(r[0], r[1])))
            .sorted(Comparator.comparing(Restriction::id))
            .collect(Collectors.toList());
    if (restrs.getLast().id() != n) {
      restrs.add(new Restriction(n, Integer.MAX_VALUE));
    }

    int[] heights = restrs.stream().mapToInt(Restriction::maxHeight).toArray();
    for (int i = 1; i < heights.length; ++i) {
      heights[i] =
          Math.min(heights[i], heights[i - 1] + (restrs.get(i).id() - restrs.get(i - 1).id()));
    }
    for (int i = heights.length - 2; i >= 0; --i) {
      heights[i] =
          Math.min(heights[i], heights[i + 1] + (restrs.get(i + 1).id() - restrs.get(i).id()));
    }

    return Math.max(
        Arrays.stream(heights).max().getAsInt(),
        IntStream.range(0, heights.length - 1)
            .map(
                i -> {
                  int heightDiff = Math.abs(heights[i] - heights[i + 1]);
                  int distance = restrs.get(i + 1).id() - restrs.get(i).id();
                  int freeDistance = distance - heightDiff;

                  return Math.max(heights[i], heights[i + 1]) + freeDistance / 2;
                })
            .max()
            .getAsInt());
  }
}

record Restriction(int id, int maxHeight) {}
