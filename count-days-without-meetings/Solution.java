import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int countDays(int days, int[][] meetings) {
    Arrays.sort(meetings, Comparator.comparing(meeting -> meeting[0]));

    int result = 0;
    int maxEnd = 0;
    for (int[] meeting : meetings) {
      result += Math.max(0, meeting[0] - maxEnd - 1);
      maxEnd = Math.max(maxEnd, meeting[1]);
    }
    result += days - maxEnd;

    return result;
  }
}