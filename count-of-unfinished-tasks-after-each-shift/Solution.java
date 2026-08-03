class Solution {
  public int[] countTasks(int[] tasks, int[] shifts) {
    long[] taskPrefixSums = new long[tasks.length + 1];
    for (int i = 1; i < taskPrefixSums.length; ++i) {
      taskPrefixSums[i] = taskPrefixSums[i - 1] + tasks[i - 1];
    }

    int[] result = new int[shifts.length];
    int taskIndex = 0;
    int rest = tasks[0];
    for (int i = 0; i < result.length; ++i) {
      if (shifts[i] < rest) {
        rest -= shifts[i];
        result[i] = tasks.length - taskIndex;
      } else {
        shifts[i] -= rest;
        ++taskIndex;

        int wholeTaskNum = findWholeTaskNum(taskPrefixSums, taskIndex, shifts[i]);
        shifts[i] -= computeTaskRangeSum(taskPrefixSums, taskIndex, taskIndex + wholeTaskNum - 1);
        taskIndex += wholeTaskNum;

        if (taskIndex == tasks.length) {
          result[i] = 0;

          taskIndex = 0;
          rest = tasks[0];
        } else {
          result[i] = tasks.length - taskIndex;
          rest = tasks[taskIndex] - shifts[i];
        }
      }
    }

    return result;
  }

  int findWholeTaskNum(long[] taskPrefixSums, int taskIndex, int target) {
    int taskNum = taskPrefixSums.length - 1;

    int result = 0;
    int lower = 1;
    int upper = taskNum - taskIndex;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (computeTaskRangeSum(taskPrefixSums, taskIndex, taskIndex + middle - 1) <= target) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  long computeTaskRangeSum(long[] taskPrefixSums, int beginIndex, int endIndex) {
    return taskPrefixSums[endIndex + 1] - taskPrefixSums[beginIndex];
  }
}