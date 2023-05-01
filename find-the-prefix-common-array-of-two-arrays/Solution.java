import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] findThePrefixCommonArray(int[] A, int[] B) {
    int[] result = new int[A.length];
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int i = 0; i < result.length; ++i) {
      valueToCount.put(A[i], valueToCount.getOrDefault(A[i], 0) + 1);
      valueToCount.put(B[i], valueToCount.getOrDefault(B[i], 0) + 1);

      result[i] = (int) valueToCount.values().stream().filter(count -> count == 2).count();
    }

    return result;
  }
}
