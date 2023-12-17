import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
  static List<Integer> PALINDROMES = buildPalindromes();

  public long minimumCost(int[] nums) {
    Arrays.sort(nums);
    long total = Arrays.stream(nums).asLongStream().sum();

    long result = Long.MAX_VALUE;
    int leftCount = 0;
    long leftSum = 0;
    for (int palindrome : PALINDROMES) {
      while (leftCount != nums.length && nums[leftCount] <= palindrome) {
        leftSum += nums[leftCount];
        ++leftCount;
      }

      result =
          Math.min(
              result,
              ((long) palindrome * leftCount - leftSum)
                  + (total - leftSum - (long) palindrome * (nums.length - leftCount)));
    }

    return result;
  }

  static List<Integer> buildPalindromes() {
    List<Integer> palindromes = new ArrayList<>();
    int minHalf = 1;
    for (int i = 0; i < 5; ++i) {
      for (int half = minHalf; half < minHalf * 10; ++half) {
        if (i != 4) {
          palindromes.add(
              Integer.parseInt(
                  String.valueOf(half) + new StringBuilder().append(half).reverse().toString()));
        }

        palindromes.add(
            Integer.parseInt(
                String.valueOf(half)
                    + new StringBuilder().append(half).reverse().toString().substring(1)));
      }

      minHalf *= 10;
    }
    palindromes.add(1_000_000_001);
    Collections.sort(palindromes);

    return palindromes;
  }
}