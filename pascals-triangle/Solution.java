import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < numRows; ++i) {
      List<Integer> row = new ArrayList<>();
      row.add(1);
      if (!result.isEmpty()) {
        for (int j = 0; j < result.getLast().size() - 1; ++j) {
          row.add(result.getLast().get(j) + result.getLast().get(j + 1));
        }

        row.add(1);
      }
      result.add(row);
    }

    return result;
  }
}
