import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int LIMIT = 1_000_000_000;

  static Map<Integer, Range> operationNumToRange;

  static {
    precompute();
  }

  static void precompute() {
    operationNumToRange = new HashMap<>();
    int min = 0;
    int max = 0;
    for (int operationNum = 1; ; ++operationNum) {
      min = max + 1;
      max = min * 4 - 1;

      operationNumToRange.put(operationNum, new Range(min, max));

      if (max >= LIMIT) {
        break;
      }
    }
  }

  public long minOperations(int[][] queries) {
    return Arrays.stream(queries)
        .mapToLong(query -> computeMinOperationNum(query[0], query[1]))
        .sum();
  }

  long computeMinOperationNum(int left, int right) {
    Map<Integer, Integer> operationNumToCount = new HashMap<>();
    for (int operationNum : operationNumToRange.keySet()) {
      int min = Math.max(left, operationNumToRange.get(operationNum).min());
      int max = Math.min(right, operationNumToRange.get(operationNum).max());
      if (min <= max) {
        operationNumToCount.put(operationNum, max - min + 1);
      }
    }

    return (operationNumToCount.keySet().stream()
                .mapToLong(
                    operationNum -> (long) operationNum * operationNumToCount.get(operationNum))
                .sum()
            + 1)
        / 2;
  }
}

record Range(int min, int max) {}
