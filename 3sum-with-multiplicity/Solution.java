import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int threeSumMulti(int[] arr, int target) {
    int result = 0;
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int i = 0; i < arr.length; ++i) {
      for (int j = i + 1; j < arr.length; ++j) {
        result = addMod(result, valueToCount.getOrDefault(target - arr[i] - arr[j], 0));
      }

      valueToCount.put(arr[i], valueToCount.getOrDefault(arr[i], 0) + 1);
    }

    return result;
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }
}
