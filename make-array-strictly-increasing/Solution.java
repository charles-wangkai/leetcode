import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int makeArrayIncreasing(int[] arr1, int[] arr2) {
    Arrays.sort(arr2);

    Map<Integer, Integer> operationNumToLastValue = Map.of(0, Integer.MIN_VALUE);
    for (int value : arr1) {
      Map<Integer, Integer> nextOperationNumToLastValue = new HashMap<>();
      for (int operationNum : operationNumToLastValue.keySet()) {
        int lastValue = operationNumToLastValue.get(operationNum);

        if (value > lastValue) {
          updateMap(nextOperationNumToLastValue, operationNum, value);
        }

        Integer largerValue = findLargerValue(arr2, lastValue);
        if (largerValue != null) {
          updateMap(nextOperationNumToLastValue, operationNum + 1, largerValue);
        }
      }

      operationNumToLastValue = nextOperationNumToLastValue;
    }

    return operationNumToLastValue.keySet().stream().mapToInt(Integer::intValue).min().orElse(-1);
  }

  void updateMap(Map<Integer, Integer> operationNumToLastValue, int operationNum, int lastValue) {
    operationNumToLastValue.put(
        operationNum,
        Math.min(operationNumToLastValue.getOrDefault(operationNum, Integer.MAX_VALUE), lastValue));
  }

  Integer findLargerValue(int[] arr2, int value) {
    int index = -1;
    int lower = 0;
    int upper = arr2.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (arr2[middle] > value) {
        index = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return (index == -1) ? null : arr2[index];
  }
}
