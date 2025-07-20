import java.util.stream.IntStream;

class Solution {
  public long splitArray(int[] nums) {
    return Math.abs(
        IntStream.range(0, nums.length)
            .map(i -> (isPrime(i) ? 1 : -1) * nums[i])
            .asLongStream()
            .sum());
  }

  boolean isPrime(int x) {
    if (x < 2) {
      return false;
    }

    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}