import java.util.Arrays;
import java.util.NavigableMap;
import java.util.TreeMap;

class Solution {
  public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
    Arrays.sort(tasks);
    Arrays.sort(workers);

    int result = 0;
    int lower = 1;
    int upper = Math.min(tasks.length, workers.length);
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(tasks, workers, pills, strength, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[] tasks, int[] workers, int pills, int strength, int matchedNum) {
    NavigableMap<Integer, Integer> workerToCount = new TreeMap<>();
    for (int i = workers.length - 1; i >= workers.length - matchedNum; --i) {
      workerToCount.put(workers[i], workerToCount.getOrDefault(workers[i], 0) + 1);
    }

    for (int i = matchedNum - 1; i >= 0; --i) {
      Integer worker = workerToCount.ceilingKey(tasks[i]);
      if (worker == null) {
        worker = workerToCount.ceilingKey(tasks[i] - strength);
        if (pills == 0 || worker == null) {
          return false;
        }

        --pills;
      }

      workerToCount.put(worker, workerToCount.get(worker) - 1);
      workerToCount.remove(worker, 0);
    }

    return true;
  }
}
