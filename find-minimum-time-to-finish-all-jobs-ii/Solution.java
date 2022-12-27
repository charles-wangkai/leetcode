// https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs-ii/solutions/2215406/java-longest-task-fastest-person-explained/

import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minimumTime(int[] jobs, int[] workers) {
    Arrays.sort(jobs);
    Arrays.sort(workers);

    return IntStream.range(0, jobs.length)
        .map(i -> (jobs[i] + workers[i] - 1) / workers[i])
        .max()
        .getAsInt();
  }
}
