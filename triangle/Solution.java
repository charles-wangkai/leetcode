import java.util.ArrayList;
import java.util.List;

class Solution {
  public int minimumTotal(List<List<Integer>> triangle) {
    List<Integer> dp = triangle.get(0);
    for (int i = 1; i < triangle.size(); ++i) {
      List<Integer> row = triangle.get(i);

      List<Integer> nextDp = new ArrayList<>();
      nextDp.add(dp.get(0) + row.get(0));
      for (int j = 0; j < dp.size() - 1; ++j) {
        nextDp.add(Math.min(dp.get(j), dp.get(j + 1)) + row.get(j + 1));
      }
      nextDp.add(dp.getLast() + row.getLast());

      dp = nextDp;
    }

    return dp.stream().mapToInt(Integer::intValue).min().getAsInt();
  }
}
