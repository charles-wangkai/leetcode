import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public long minOperations(int[] nums, int x, int k) {
    long[] operationNums = buildOperationNums(nums, x);

    long[][] dp = new long[nums.length + 1][];
    dp[0] = new long[k + 1];
    Arrays.fill(dp[0], Long.MAX_VALUE);
    dp[0][0] = 0;

    for (int i = 1; i < dp.length; ++i) {
      dp[i] = dp[i - 1].clone();

      if (i >= x) {
        for (int j = 0; j < k; ++j) {
          if (dp[i - x][j] != Long.MAX_VALUE) {
            dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i - x][j] + operationNums[i - 1]);
          }
        }
      }
    }

    return dp[dp.length - 1][k];
  }

  long[] buildOperationNums(int[] nums, int x) {
    long[] result = new long[nums.length];
    Window window = new Window();
    for (int i = 0; i < result.length; ++i) {
      window.add(nums[i]);

      if (i >= x - 1) {
        result[i] = window.computeOperationNum();

        window.remove(nums[i - x + 1]);
      }
    }

    return result;
  }
}

class Window {
  Half lowerHalf = new Half();
  Half upperHalf = new Half();

  void add(int value) {
    ((upperHalf.size == 0 || value >= upperHalf.valueToCount.firstKey()) ? upperHalf : lowerHalf)
        .add(value);

    balance();
  }

  void remove(int value) {
    (lowerHalf.valueToCount.containsKey(value) ? lowerHalf : upperHalf).remove(value);

    balance();
  }

  long computeOperationNum() {
    return upperHalf.sum
        - lowerHalf.sum
        - ((upperHalf.size == lowerHalf.size) ? 0 : upperHalf.valueToCount.firstKey());
  }

  void balance() {
    if (lowerHalf.size == upperHalf.size + 1) {
      int value = lowerHalf.valueToCount.lastKey();
      lowerHalf.remove(value);
      upperHalf.add(value);
    } else if (upperHalf.size == lowerHalf.size + 2) {
      int value = upperHalf.valueToCount.firstKey();
      upperHalf.remove(value);
      lowerHalf.add(value);
    }
  }
}

class Half {
  SortedMap<Integer, Integer> valueToCount = new TreeMap<>();
  int size = 0;
  long sum = 0;

  void add(int value) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    ++size;
    sum += value;
  }

  void remove(int value) {
    valueToCount.put(value, valueToCount.get(value) - 1);
    valueToCount.remove(value, 0);

    --size;
    sum -= value;
  }
}