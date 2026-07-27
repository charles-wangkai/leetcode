import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<List<Integer>> aggregateTimeSeries(int[][] series1, int[][] series2) {
    List<List<Integer>> result = new ArrayList<>();
    int index1 = 0;
    int index2 = 0;
    while (index1 != series1.length || index2 != series2.length) {
      int timestamp =
          (index2 == series2.length
                  || (index1 != series1.length && series1[index1][0] < series2[index2][0]))
              ? series1[index1][0]
              : series2[index2][0];
      int valueSum =
          ((index1 == series1.length) ? 0 : series1[index1][1])
              + ((index2 == series2.length) ? 0 : series2[index2][1]);

      result.add(List.of(timestamp, valueSum));

      if (index1 != series1.length && series1[index1][0] == timestamp) {
        ++index1;
      }
      if (index2 != series2.length && series2[index2][0] == timestamp) {
        ++index2;
      }
    }

    return result;
  }
}