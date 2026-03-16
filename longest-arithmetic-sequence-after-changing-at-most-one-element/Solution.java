import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  public int longestArithmetic(int[] nums) {
    int[] leftLengths = new int[nums.length];
    for (int i = 0; i < leftLengths.length; ++i) {
      leftLengths[i] = (i == 0) ? 1 : 2;
      if (i >= 2 && nums[i - 1] * 2 == nums[i - 2] + nums[i]) {
        leftLengths[i] = leftLengths[i - 1] + 1;
      }
    }

    int[] rightLengths = new int[nums.length];
    for (int i = rightLengths.length - 1; i >= 0; --i) {
      rightLengths[i] = (i == rightLengths.length - 1) ? 1 : 2;
      if (i <= rightLengths.length - 3 && nums[i + 1] * 2 == nums[i] + nums[i + 2]) {
        rightLengths[i] = rightLengths[i + 1] + 1;
      }
    }

    return Stream.of(
            IntStream.range(0, nums.length).map(i -> Math.max(leftLengths[i], rightLengths[i])),
            IntStream.range(1, nums.length).map(i -> 1 + leftLengths[i - 1]),
            IntStream.range(0, nums.length - 1).map(i -> 1 + rightLengths[i + 1]),
            IntStream.range(1, nums.length - 1)
                .map(
                    i -> {
                      int sum = nums[i - 1] + nums[i + 1];
                      if (sum % 2 != 0) {
                        return -1;
                      }

                      int middle = sum / 2;
                      int leftLength =
                          (i != 1 && nums[i - 1] * 2 == nums[i - 2] + middle)
                              ? leftLengths[i - 1]
                              : 1;
                      int rightLength =
                          (i != nums.length - 2 && nums[i + 1] * 2 == middle + nums[i + 2])
                              ? rightLengths[i + 1]
                              : 1;

                      return 1 + leftLength + rightLength;
                    }))
        .flatMapToInt(Function.identity())
        .max()
        .getAsInt();
  }
}