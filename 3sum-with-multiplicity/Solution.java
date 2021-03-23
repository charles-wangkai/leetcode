import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int threeSumMulti(int[] A, int target) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : A) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    int result = 0;
    for (int i = 0; i < A.length; ++i) {
      update(valueToCount, A[i], -1);

      for (int j = i + 1; j < A.length; ++j) {
        update(valueToCount, A[j], -1);

        result = addMod(result, valueToCount.getOrDefault(target - A[i] - A[j], 0));
      }

      for (int j = i + 1; j < A.length; ++j) {
        update(valueToCount, A[j], 1);
      }
    }

    return result;
  }

  void update(Map<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }
}
