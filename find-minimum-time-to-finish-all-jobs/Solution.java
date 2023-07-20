import java.util.Arrays;
import java.util.Comparator;

class Solution {
  int minTime;

  public int minimumTimeRequired(int[] jobs, int k) {
    minTime = Arrays.stream(jobs).sum();
    jobs = Arrays.stream(jobs).boxed().sorted(Comparator.reverseOrder()).mapToInt(x -> x).toArray();
    int[] sums = new int[k];

    search(jobs, sums, 0);

    return minTime;
  }

  void search(int[] jobs, int[] sums, int index) {
    if (index == jobs.length) {
      minTime = Arrays.stream(sums).max().getAsInt();

      return;
    }

    for (int i = 0; i < sums.length; ++i) {
      if (sums[i] + jobs[index] < minTime) {
        sums[i] += jobs[index];
        search(jobs, sums, index + 1);
        sums[i] -= jobs[index];
      }
      if (sums[i] == 0) {
        break;
      }
    }
  }
}
