// https://leetcode.com/problems/xor-after-range-multiplication-queries-ii/solutions/7090800/python-sqrt-decomposition/

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int xorAfterQueries(int[] nums, int[][] queries) {
    int threshold = (int) Math.ceil(Math.sqrt(nums.length));

    Map<Integer, int[]> stepToEvents = new HashMap<>();
    for (int[] query : queries) {
      int l = query[0];
      int r = query[1];
      int k = query[2];
      int v = query[3];

      if (k <= threshold) {
        if (!stepToEvents.containsKey(k)) {
          int[] events = new int[nums.length];
          Arrays.fill(events, 1);

          stepToEvents.put(k, events);
        }

        int[] events = stepToEvents.get(k);

        events[l] = multiplyMod(events[l], v);

        int r2 = r + Math.floorMod(-(r - l), k);
        if (r2 == r) {
          r2 += k;
        }
        if (r2 < events.length) {
          events[r2] = multiplyMod(events[r2], inverseMod(v));
        }
      } else {
        for (int i = l; i <= r; i += k) {
          nums[i] = multiplyMod(nums[i], v);
        }
      }
    }

    for (int step : stepToEvents.keySet()) {
      int[] events = stepToEvents.get(step);

      for (int remainder = 0; remainder < step; ++remainder) {
        int factor = 1;
        for (int i = remainder; i < events.length; i += step) {
          factor = multiplyMod(factor, events[i]);
          nums[i] = multiplyMod(nums[i], factor);
        }
      }
    }

    return Arrays.stream(nums).reduce((acc, x) -> acc ^ x).getAsInt();
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int inverseMod(int x) {
    return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(MODULUS)).intValue();
  }
}
