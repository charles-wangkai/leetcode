import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int distinctPrimeFactors(int[] nums) {
    return (int)
        IntStream.rangeClosed(2, Arrays.stream(nums).max().getAsInt())
            .filter(x -> isPrime(x) && Arrays.stream(nums).anyMatch(num -> num % x == 0))
            .count();
  }

  boolean isPrime(int x) {
    return IntStream.range(2, x).allMatch(i -> x % i != 0);
  }
}
