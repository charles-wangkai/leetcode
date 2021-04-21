import java.util.ArrayList;
import java.util.List;

class Solution {
  public int minimumTotal(List<List<Integer>> triangle) {
    List<Integer> minSums = List.of();
    for (List<Integer> row : triangle) {
      List<Integer> nextMinSums = new ArrayList<>();
      if (minSums.isEmpty()) {
        nextMinSums.add(row.get(0));
      } else {
        nextMinSums.add(minSums.get(0) + row.get(0));
        for (int i = 0; i < minSums.size() - 1; ++i) {
          nextMinSums.add(Math.min(minSums.get(i), minSums.get(i + 1)) + row.get(i + 1));
        }
        nextMinSums.add(minSums.get(minSums.size() - 1) + row.get(row.size() - 1));
      }

      minSums = nextMinSums;
    }

    return minSums.stream().mapToInt(x -> x).min().getAsInt();
  }
}
