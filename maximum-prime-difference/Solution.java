import java.util.stream.IntStream;

class Solution {
  public int maximumPrimeDifference(int[] nums) {
    int[] primeIndices = IntStream.range(0, nums.length).filter(i -> isPrime(nums[i])).toArray();

    return primeIndices[primeIndices.length - 1] - primeIndices[0];
  }

  boolean isPrime(int x) {
    if (x == 1) {
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