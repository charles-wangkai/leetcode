import java.util.HashMap;
import java.util.Map;

class Solution {
  Map<Integer, Integer> cache = new HashMap<>();

  public int minOperations(int n) {
    if (!cache.containsKey(n)) {
      cache.put(
          n,
          (n == 0)
              ? 0
              : (1
                  + Math.min(
                      minOperations(n + Integer.lowestOneBit(n)),
                      minOperations(n - Integer.lowestOneBit(n)))));
    }

    return cache.get(n);
  }
}
