import java.util.Arrays;

class Solution {
  public long maxAlternatingSum(int[] nums) {
    int[] squares = Arrays.stream(nums).map(x -> x * x).sorted().toArray();

    long result = 0;
    int leftIndex = 0;
    int rightIndex = squares.length - 1;
    for (int i = 0; i < nums.length; ++i) {
      if (i % 2 == 0) {
        result += squares[rightIndex];
        --rightIndex;
      } else {
        result -= squares[leftIndex];
        ++leftIndex;
      }
    }

    return result;
  }
}