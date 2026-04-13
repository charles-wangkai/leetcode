import java.util.stream.IntStream;

class Solution {
  public int minOperations(int[] nums) {
    return IntStream.range(0, nums.length).map(i -> computeOperationNum(nums[i], i % 2 == 0)).sum();
  }

  int computeOperationNum(int value, boolean targetIsPrime) {
    int result = 0;
    while (true) {
      if (isPrime(value) == targetIsPrime) {
        return result;
      }

      ++result;
      ++value;
    }
  }

  boolean isPrime(int x) {
    if (x <= 1) {
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