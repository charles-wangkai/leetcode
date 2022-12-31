import java.util.HashMap;
import java.util.Map;

class Solution {
  public long minimumTime(int[] power) {
    Map<Integer, Long> maskToDayNum = Map.of(0, 0L);
    for (int i = 0; i < power.length; ++i) {
      Map<Integer, Long> nextMaskToDayNum = new HashMap<>();
      for (int mask : maskToDayNum.keySet()) {
        for (int j = 0; j < power.length; ++j) {
          if (((mask >> j) & 1) == 0) {
            int nextMask = mask + (1 << j);
            nextMaskToDayNum.put(
                nextMask,
                Math.min(
                    nextMaskToDayNum.getOrDefault(nextMask, Long.MAX_VALUE),
                    maskToDayNum.get(mask) + (power[j] + i) / (i + 1)));
          }
        }
      }

      maskToDayNum = nextMaskToDayNum;
    }

    return maskToDayNum.values().iterator().next();
  }
}
