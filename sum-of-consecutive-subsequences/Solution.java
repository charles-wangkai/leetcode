import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int getSum(int[] nums) {
    return addMod(
        Arrays.stream(nums).reduce(this::addMod).getAsInt(),
        addMod(computeSum(nums, 1), computeSum(nums, -1)));
  }

  int computeSum(int[] nums, int diff) {
    int[] leftNums = buildNums(nums, diff);
    int[] rightNums = reverse(buildNums(reverse(nums), -diff));

    return IntStream.range(0, nums.length)
        .map(i -> multiplyMod(nums[i], addMod(multiplyMod(leftNums[i], rightNums[i]), -1)))
        .reduce(this::addMod)
        .getAsInt();
  }

  int[] buildNums(int[] nums, int diff) {
    int[] result = new int[nums.length];
    Map<Integer, Integer> valueToNum = new HashMap<>();
    for (int i = 0; i < result.length; ++i) {
      result[i] = addMod(valueToNum.getOrDefault(nums[i] - diff, 0), 1);
      valueToNum.put(nums[i], addMod(valueToNum.getOrDefault(nums[i], 0), result[i]));
    }

    return result;
  }

  int[] reverse(int[] a) {
    return IntStream.range(0, a.length).map(i -> a[a.length - 1 - i]).toArray();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}