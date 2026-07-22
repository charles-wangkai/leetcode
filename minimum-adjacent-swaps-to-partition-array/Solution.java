import java.util.Arrays;

class Solution {
  public int minAdjacentSwaps(int[] nums, int a, int b) {
    int[] values =
        Arrays.stream(nums)
            .map(
                num -> {
                  if (num < a) {
                    return 0;
                  }
                  if (num > b) {
                    return 2;
                  }

                  return 1;
                })
            .toArray();

    long inversionNum = 0;
    int[] counts = new int[3];
    for (int value : values) {
      for (int i = value + 1; i < counts.length; ++i) {
        inversionNum += counts[i];
      }

      ++counts[value];
    }

    return (int) (inversionNum % 1_000_000_007);
  }
}