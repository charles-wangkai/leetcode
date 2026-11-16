import java.util.stream.IntStream;

class Solution {
  public int countConsistent(int[] nums) {
    int result = 0;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      for (int endIndex = beginIndex; endIndex < nums.length; ++endIndex) {
        if (IntStream.rangeClosed(beginIndex, endIndex)
                .map(i -> nums[i])
                .reduce((acc, x) -> acc & x)
                .getAsInt()
            == IntStream.rangeClosed(beginIndex, endIndex).map(i -> nums[i]).min().getAsInt()) {
          ++result;
        }
      }
    }

    return result;
  }
}