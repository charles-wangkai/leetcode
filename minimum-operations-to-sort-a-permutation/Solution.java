import java.util.stream.IntStream;

class Solution {
  public int minOperations(int[] nums) {
    int n = nums.length;

    if (n == 1) {
      return 0;
    }

    int zeroIndex = IntStream.range(0, nums.length).filter(i -> nums[i] == 0).findAny().getAsInt();
    if (IntStream.range(0, n).allMatch(i -> nums[Math.floorMod(zeroIndex + i, nums.length)] == i)) {
      return Math.min(
          computeOperationNum(n, zeroIndex, false, false),
          computeOperationNum(n, zeroIndex, true, true));
    }
    if (IntStream.range(0, n).allMatch(i -> nums[Math.floorMod(zeroIndex - i, nums.length)] == i)) {
      return Math.min(
          computeOperationNum(n, zeroIndex, false, true),
          computeOperationNum(n, zeroIndex, true, false));
    }

    return -1;
  }

  int computeOperationNum(int n, int zeroIndex, boolean startReversed, boolean stopReversed) {
    int result = (startReversed ? 1 : 0) + (stopReversed ? 1 : 0);
    int index = startReversed ? (n - 1 - zeroIndex) : zeroIndex;
    int targetIndex = stopReversed ? (n - 1) : 0;
    while (index != targetIndex) {
      index = Math.floorMod(index - 1, n);
      ++result;
    }

    return result;
  }
}