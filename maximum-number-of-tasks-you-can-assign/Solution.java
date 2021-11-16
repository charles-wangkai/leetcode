import java.util.Arrays;
import java.util.NavigableMap;
import java.util.TreeMap;

class Solution {
  public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
    int[] sortedTasks = Arrays.stream(tasks).boxed().sorted().mapToInt(x -> x).toArray();
    int[] sortedWorkers = Arrays.stream(workers).boxed().sorted().mapToInt(x -> x).toArray();

    int result = 0;
    int lower = 1;
    int upper = Math.min(tasks.length, workers.length);
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(sortedTasks, sortedWorkers, pills, strength, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[] sortedTasks, int[] sortedWorkers, int pills, int strength, int matchedNum) {
    NavigableMap<Integer, Integer> workerToCount = new TreeMap<>();
    for (int i = sortedWorkers.length - 1; i >= sortedWorkers.length - matchedNum; --i) {
      workerToCount.put(sortedWorkers[i], workerToCount.getOrDefault(sortedWorkers[i], 0) + 1);
    }

    for (int i = matchedNum - 1; i >= 0; --i) {
      Integer worker = workerToCount.ceilingKey(sortedTasks[i]);
      if (worker == null) {
        worker = workerToCount.ceilingKey(sortedTasks[i] - strength);
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
