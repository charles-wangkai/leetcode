import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int BIT_LIMIT = 30;

  public int[][] substringXorQueries(String s, int[][] queries) {
    Map<Integer, Integer> valueToBeginIndex = new HashMap<>();
    for (int beginIndex = 0; beginIndex < s.length(); ++beginIndex) {
      for (int length = 1; length <= BIT_LIMIT && beginIndex + length <= s.length(); ++length) {
        String part = s.substring(beginIndex, beginIndex + length);
        if (part.charAt(0) == '1' || part.length() == 1) {
          int value = Integer.parseInt(part, 2);
          if (!valueToBeginIndex.containsKey(value)) {
            valueToBeginIndex.put(value, beginIndex);
          }
        }
      }
    }

    return Arrays.stream(queries)
        .map(
            query -> {
              int value = query[0] ^ query[1];

              return valueToBeginIndex.containsKey(value)
                  ? new int[] {
                    valueToBeginIndex.get(value),
                    valueToBeginIndex.get(value) + Integer.toBinaryString(value).length() - 1
                  }
                  : new int[] {-1, -1};
            })
        .toArray(int[][]::new);
  }
}
