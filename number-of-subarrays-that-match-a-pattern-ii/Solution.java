import static java.util.Map.entry;

import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  static final Map<Integer, Integer> VALUE_TO_MAPPED =
      Map.ofEntries(entry(0, 0), entry(1, 1), entry(-1, 2));
  static final int BASE_1 = 31;
  static final int BASE_2 = 37;

  public int countMatchingSubarrays(int[] nums, int[] pattern) {
    int[] values =
        IntStream.range(0, nums.length - 1)
            .map(i -> Integer.compare(nums[i + 1], nums[i]))
            .toArray();

    long targetHash1 = 0;
    long targetHash2 = 0;
    for (int p : pattern) {
      targetHash1 = targetHash1 * BASE_1 + VALUE_TO_MAPPED.get(p);
      targetHash2 = targetHash2 * BASE_2 + VALUE_TO_MAPPED.get(p);
    }

    long maxPlaceValue1 = 1;
    long maxPlaceValue2 = 1;
    for (int i = 0; i < pattern.length - 1; ++i) {
      maxPlaceValue1 *= BASE_1;
      maxPlaceValue2 *= BASE_2;
    }

    long hash1 = 0;
    long hash2 = 0;
    for (int i = 0; i < pattern.length - 1; ++i) {
      hash1 = hash1 * BASE_1 + VALUE_TO_MAPPED.get(values[i]);
      hash2 = hash2 * BASE_2 + VALUE_TO_MAPPED.get(values[i]);
    }

    int result = 0;
    for (int i = pattern.length - 1; i < values.length; ++i) {
      hash1 = hash1 * BASE_1 + VALUE_TO_MAPPED.get(values[i]);
      hash2 = hash2 * BASE_2 + VALUE_TO_MAPPED.get(values[i]);

      if (hash1 == targetHash1 && hash2 == targetHash2) {
        ++result;
      }

      hash1 -= maxPlaceValue1 * VALUE_TO_MAPPED.get(values[i - pattern.length + 1]);
      hash2 -= maxPlaceValue2 * VALUE_TO_MAPPED.get(values[i - pattern.length + 1]);
    }

    return result;
  }
}