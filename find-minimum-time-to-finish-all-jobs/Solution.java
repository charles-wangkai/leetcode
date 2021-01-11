import java.util.Arrays;
import java.util.Comparator;

class Solution {
  int solution;

  public int minimumTimeRequired(int[] jobs, int k) {
    solution = Arrays.stream(jobs).sum();
    jobs = Arrays.stream(jobs).boxed().sorted(Comparator.reverseOrder()).mapToInt(x -> x).toArray();
    int[] sums = new int[k];

    search(jobs, sums, 0);

    return solution;
  }

  void search(int[] jobs, int[] sums, int index) {
    if (index == jobs.length) {
      solution = Arrays.stream(sums).max().getAsInt();

      return;
    }

    for (int i = 0; i < sums.length; ++i) {
      if (sums[i] + jobs[index] < solution) {
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
