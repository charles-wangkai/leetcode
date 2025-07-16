import java.util.stream.IntStream;

class Solution {
  public int maximumLength(int[] nums) {
    return IntStream.of(
            computeLength(nums, 0, 0),
            computeLength(nums, 0, 1),
            computeLength(nums, 1, 0),
            computeLength(nums, 1, 1))
        .max()
        .getAsInt();
  }

  int computeLength(int[] nums, int firstRemainder, int secondRemainder) {
    int result = 0;
    for (int num : nums) {
      if ((result % 2 == 0 && num % 2 == firstRemainder)
          || (result % 2 == 1 && num % 2 == secondRemainder)) {
        ++result;
      }
    }

    return result;
  }
}