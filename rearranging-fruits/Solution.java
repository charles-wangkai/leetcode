import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public long minCost(int[] basket1, int[] basket2) {
    Map<Integer, Integer> valueToDiff = new HashMap<>();
    for (int value : basket1) {
      valueToDiff.put(value, valueToDiff.getOrDefault(value, 0) + 1);
    }
    for (int value : basket2) {
      valueToDiff.put(value, valueToDiff.getOrDefault(value, 0) - 1);
    }

    List<Integer> rests1 = new ArrayList<>();
    List<Integer> rests2 = new ArrayList<>();
    for (int value : valueToDiff.keySet()) {
      int diff = valueToDiff.get(value);
      if (diff % 2 != 0) {
        return -1;
      }

      if (diff > 0) {
        for (int i = 0; i < diff / 2; ++i) {
          rests1.add(value);
        }
      } else {
        for (int i = 0; i < -diff / 2; ++i) {
          rests2.add(value);
        }
      }
    }
    Collections.sort(rests1);
    Collections.sort(rests2);

    long result = 0;
    int commonCost =
        IntStream.concat(Arrays.stream(basket1), Arrays.stream(basket2)).min().getAsInt() * 2;
    int beginIndex1 = 0;
    int beginIndex2 = 0;
    for (int i = 0; i < rests1.size(); ++i) {
      if (commonCost <= Math.min(rests1.get(beginIndex1), rests2.get(beginIndex2))) {
        result += commonCost;
        ++beginIndex1;
        ++beginIndex2;
      } else if (rests1.get(beginIndex1) <= rests2.get(beginIndex2)) {
        result += rests1.get(beginIndex1);
        ++beginIndex1;
      } else {
        result += rests2.get(beginIndex2);
        ++beginIndex2;
      }
    }

    return result;
  }
}
