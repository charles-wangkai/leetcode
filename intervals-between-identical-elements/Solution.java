import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public long[] getDistances(int[] arr) {
    Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
    for (int i = 0; i < arr.length; ++i) {
      if (!valueToIndices.containsKey(arr[i])) {
        valueToIndices.put(arr[i], new ArrayList<>());
      }
      valueToIndices.get(arr[i]).add(i);
    }

    long[] result = new long[arr.length];
    for (List<Integer> indices : valueToIndices.values()) {
      long[] intervalSums = computeIntervalSums(indices);
      for (int i = 0; i < indices.size(); ++i) {
        result[indices.get(i)] += intervalSums[i];
      }
    }

    return result;
  }

  long[] computeIntervalSums(List<Integer> indices) {
    long[] result = new long[indices.size()];

    long leftSum = 0;
    for (int i = 0; i < indices.size(); ++i) {
      result[i] += (long) indices.get(i) * i - leftSum;
      leftSum += indices.get(i);
    }

    long rightSum = 0;
    for (int i = indices.size() - 1; i >= 0; --i) {
      result[i] += rightSum - indices.get(i) * (indices.size() - 1L - i);
      rightSum += indices.get(i);
    }

    return result;
  }
}