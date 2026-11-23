// See hints

import java.util.stream.IntStream;

class Solution {
  static final int BIT_NUM = 17;

  public int minSetBits(int[] nums, int k) {
    int[] oneCounts = new int[BIT_NUM];
    for (int i = 0; i < k - 1; ++i) {
      update(oneCounts, 1, nums[i]);
    }

    int[] values = new int[nums.length - k + 1];
    for (int i = 0; i < values.length; ++i) {
      update(oneCounts, 1, nums[i + k - 1]);

      for (int b = 0; b < BIT_NUM; ++b) {
        if (oneCounts[b] == k) {
          values[i] += 1 << b;
        }
      }

      update(oneCounts, -1, nums[i]);
    }

    boolean[] bads = new boolean[1 << BIT_NUM];
    for (int value : values) {
      bads[((1 << BIT_NUM) - 1) ^ value] = true;
    }

    for (int mask = bads.length - 1; mask >= 1; --mask) {
      if (bads[mask]) {
        for (int b = 0; b < BIT_NUM; ++b) {
          if (((mask >> b) & 1) == 1) {
            bads[mask - (1 << b)] = true;
          }
        }
      }
    }

    return IntStream.range(0, bads.length)
        .filter(i -> !bads[i])
        .map(Integer::bitCount)
        .min()
        .orElse(-1);
  }

  void update(int[] counts, int delta, int value) {
    for (int b = 0; b < counts.length; ++b) {
      if (((value >> b) & 1) == 1) {
        counts[b] += delta;
      }
    }
  }
}