// See hints

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

class Solution {
  static final Random RANDOM = new Random();

  public boolean[] validSubarrays(int[] nums, int k, int l0, int r0, int q) {
    int n = nums.length;

    long[] prefixXors = buildPrefixXors(nums);

    int[] leftIndices = buildLeftIndices(nums, k);
    int[] rightIndices = buildRightIndices(nums, k);

    boolean[] result = new boolean[q];
    int l = l0;
    int r = r0;
    for (int i = 0; i < result.length; ++i) {
      result[i] =
          r >= leftIndices[l] && r <= rightIndices[l] && computeRangeXors(prefixXors, l, r) == 0;

      int g = result[i] ? (l + r) : (r - l);
      int nextL = (l ^ g) % n;
      int nextR = (r ^ g) % n;
      if (nextL > nextR) {
        int temp = nextL;
        nextL = nextR;
        nextR = temp;
      }
      l = nextL;
      r = nextR;
    }

    return result;
  }

  int[] buildRightIndices(int[] nums, int k) {
    int[] rightIndices = new int[nums.length];
    int rightIndex = -1;
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int i = 0; i < rightIndices.length; ++i) {
      while (rightIndex != nums.length
          && (valueToCount.size() < k
              || (rightIndex != nums.length - 1
                  && valueToCount.containsKey(nums[rightIndex + 1])))) {
        ++rightIndex;

        if (rightIndex != nums.length) {
          updateMap(valueToCount, nums[rightIndex], 1);
        }
      }

      rightIndices[i] = rightIndex;

      updateMap(valueToCount, nums[i], -1);
    }

    return rightIndices;
  }

  int[] buildLeftIndices(int[] nums, int k) {
    int[] leftIndices = new int[nums.length];
    int leftIndex = -1;
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int i = 0; i < leftIndices.length; ++i) {
      while (leftIndex != nums.length && valueToCount.size() < k) {
        ++leftIndex;

        if (leftIndex != nums.length) {
          updateMap(valueToCount, nums[leftIndex], 1);
        }
      }

      leftIndices[i] = leftIndex;

      updateMap(valueToCount, nums[i], -1);
    }

    return leftIndices;
  }

  void updateMap(Map<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }

  long[] buildPrefixXors(int[] nums) {
    Map<Integer, Long> valueToMapped =
        Arrays.stream(nums)
            .distinct()
            .boxed()
            .collect(Collectors.toMap(num -> num, num -> RANDOM.nextLong()));

    long[] prefixXors = new long[nums.length + 1];
    for (int i = 1; i < prefixXors.length; ++i) {
      prefixXors[i] = prefixXors[i - 1] ^ valueToMapped.get(nums[i - 1]);
    }

    return prefixXors;
  }

  long computeRangeXors(long[] prefixXors, int beginIndex, int endIndex) {
    return prefixXors[endIndex + 1] ^ prefixXors[beginIndex];
  }
}