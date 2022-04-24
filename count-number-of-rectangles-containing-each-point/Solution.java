import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  static final int HEIGHT_LIMIT = 100;

  public int[] countRectangles(int[][] rectangles, int[][] points) {
    @SuppressWarnings("unchecked")
    List<Integer>[] xLists = new List[HEIGHT_LIMIT + 1];
    for (int i = 0; i < xLists.length; ++i) {
      xLists[i] = new ArrayList<>();
    }
    for (int[] rectangle : rectangles) {
      xLists[rectangle[1]].add(rectangle[0]);
    }
    for (int i = 0; i < xLists.length; ++i) {
      Collections.sort(xLists[i]);
    }

    return Arrays.stream(points)
        .mapToInt(
            point ->
                IntStream.range(point[1], xLists.length)
                    .map(i -> computeLessEqualNum(xLists[i], point[0]))
                    .sum())
        .toArray();
  }

  int computeLessEqualNum(List<Integer> xList, int pointX) {
    int index = Collections.binarySearch(xList, pointX);
    if (index < 0) {
      index = -1 - index;
    }

    return xList.size() - index;
  }
}