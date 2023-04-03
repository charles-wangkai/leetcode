import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
  public long makeSubKSumEqual(int[] arr, int k) {
    long result = 0;
    boolean[] visited = new boolean[arr.length];
    for (int i = 0; i < visited.length; ++i) {
      if (!visited[i]) {
        List<Integer> values = new ArrayList<>();
        for (int j = i; !visited[j]; j = (j + k) % visited.length) {
          visited[j] = true;
          values.add(arr[j]);
        }

        result += computeOperationNum(values);
      }
    }

    return result;
  }

  long computeOperationNum(List<Integer> values) {
    Collections.sort(values);

    long result = 0;
    for (int i = 0, j = values.size() - 1; i < j; ++i, --j) {
      result += values.get(j) - values.get(i);
    }

    return result;
  }
}
