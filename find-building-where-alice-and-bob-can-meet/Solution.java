import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
    int[] sortedQueryIndices =
        IntStream.range(0, queries.length)
            .boxed()
            .sorted(
                Comparator.<Integer, Integer>comparing(i -> Math.max(queries[i][0], queries[i][1]))
                    .reversed())
            .mapToInt(Integer::intValue)
            .toArray();

    int[] result = new int[queries.length];
    int index = 0;
    List<Integer> heightIndices = new ArrayList<>();
    for (int i = heights.length - 1; i >= 0; --i) {
      while (!heightIndices.isEmpty() && heights[i] >= heights[heightIndices.getLast()]) {
        heightIndices.removeLast();
      }
      heightIndices.add(i);

      while (index != sortedQueryIndices.length
          && Math.max(queries[sortedQueryIndices[index]][0], queries[sortedQueryIndices[index]][1])
              == i) {
        result[sortedQueryIndices[index]] =
            findBuilding(
                heights,
                heightIndices,
                computeTargetHeight(
                    heights,
                    queries[sortedQueryIndices[index]][0],
                    queries[sortedQueryIndices[index]][1]));
        ++index;
      }
    }

    return result;
  }

  int computeTargetHeight(int[] heights, int index1, int index2) {
    if (index1 > index2) {
      return computeTargetHeight(heights, index2, index1);
    }

    return Math.max(heights[index1] + ((index1 == index2) ? 0 : 1), heights[index2]);
  }

  int findBuilding(int[] heights, List<Integer> heightIndices, int targetHeight) {
    int result = -1;
    int lower = 0;
    int upper = heightIndices.size() - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (heights[heightIndices.get(middle)] >= targetHeight) {
        result = heightIndices.get(middle);
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}
