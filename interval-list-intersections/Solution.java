import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
    List<int[]> result = new ArrayList<>();
    int index1 = 0;
    int index2 = 0;
    while (index1 != firstList.length && index2 != secondList.length) {
      int[] interval1 = firstList[index1];
      int[] interval2 = secondList[index2];

      if (interval1[1] <= interval2[1]) {
        if (interval2[0] <= interval1[1]) {
          result.add(new int[] {Math.max(interval1[0], interval2[0]), interval1[1]});
        }

        ++index1;
      } else {
        if (interval1[0] <= interval2[1]) {
          result.add(new int[] {Math.max(interval1[0], interval2[0]), interval2[1]});
        }

        ++index2;
      }
    }

    return result.toArray(new int[0][]);
  }
}
