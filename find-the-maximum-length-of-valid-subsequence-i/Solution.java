import java.util.stream.IntStream;

class Solution {
  public int maximumLength(int[] nums) {
    return IntStream.of(
            computeLength(nums, false, false),
            computeLength(nums, false, true),
            computeLength(nums, true, false),
            computeLength(nums, true, true))
        .max()
        .getAsInt();
  }

  int computeLength(int[] nums, boolean firstEven, boolean secondEven) {
    int result = 0;
    for (int num : nums) {
      if ((result % 2 == 0 && (num % 2 == 0) == firstEven)
          || (result % 2 == 1 && (num % 2 == 0) == secondEven)) {
        ++result;
      }
    }

    return result;
  }
}