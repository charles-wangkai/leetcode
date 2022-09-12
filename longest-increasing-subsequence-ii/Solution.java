import java.util.Arrays;

class Solution {
  static final int LIMIT = 100000;

  public int lengthOfLIS(int[] nums, int k) {
    int[] lengths = new int[LIMIT + 1];
    int[] segmentTree = new int[4 * Integer.highestOneBit(LIMIT)];
    for (int num : nums) {
      lengths[num] =
          Math.max(lengths[num], findMax(segmentTree, num - k, num - 1, 0, 0, LIMIT) + 1);
      update(segmentTree, num, lengths[num], 0, 0, LIMIT);
    }

    return Arrays.stream(lengths).max().getAsInt();
  }

  void update(int[] segmentTree, int x, int value, int index, int lower, int upper) {
    if (x >= lower && x <= upper) {
      segmentTree[index] = Math.max(segmentTree[index], value);

      if (lower != upper) {
        int middle = (lower + upper) / 2;
        update(segmentTree, x, value, index * 2 + 1, lower, middle);
        update(segmentTree, x, value, index * 2 + 2, middle + 1, upper);
      }
    }
  }

  int findMax(int[] segmentTree, int minX, int maxX, int index, int lower, int upper) {
    minX = Math.max(minX, lower);
    maxX = Math.min(maxX, upper);
    if (minX > maxX) {
      return 0;
    }
    if (minX == lower && maxX == upper) {
      return segmentTree[index];
    }

    int middle = (lower + upper) / 2;

    return Math.max(
        findMax(segmentTree, minX, maxX, index * 2 + 1, lower, middle),
        findMax(segmentTree, minX, maxX, index * 2 + 2, middle + 1, upper));
  }
}