import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maximumTeamSize(int[] startTime, int[] endTime) {
    int n = startTime.length;

    int[] sortedStartTimes = Arrays.stream(startTime).sorted().toArray();
    int[] sortedEndTimes = Arrays.stream(endTime).sorted().toArray();

    return IntStream.range(0, n)
        .map(
            i ->
                n
                    - findLessNum(sortedEndTimes, startTime[i])
                    - findGreaterNum(sortedStartTimes, endTime[i]))
        .max()
        .getAsInt();
  }

  int findLessNum(int[] a, int target) {
    int index = -1;
    int lower = 0;
    int upper = a.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (a[middle] < target) {
        index = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return index + 1;
  }

  int findGreaterNum(int[] a, int target) {
    int index = a.length;
    int lower = 0;
    int upper = a.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (a[middle] > target) {
        index = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return a.length - index;
  }
}