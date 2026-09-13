import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;

@SuppressWarnings("unchecked")
class Solution {
  static NavigableSet<Integer>[] PALINDROME_SETS;

  static {
    PALINDROME_SETS = new TreeSet[2];
    for (int i = 0; i < PALINDROME_SETS.length; ++i) {
      PALINDROME_SETS[i] = new TreeSet<>();
    }

    for (int i = 1; i <= 100000; i *= 10) {
      for (int half = i; half < i * 10; ++half) {
        String s = String.valueOf(half);
        for (String candidate :
            new String[] {
              s + new StringBuilder(s).reverse().toString(),
              s + new StringBuilder(s).reverse().substring(1)
            }) {
          long value = Long.parseLong(candidate);
          if (value <= Integer.MAX_VALUE) {
            PALINDROME_SETS[(int) (value % 2)].add((int) value);
          }
        }
      }
    }
  }

  public long minOperations(int[] nums) {
    return Arrays.stream(nums)
        .map(
            x ->
                Math.min(x - PALINDROME_SETS[x % 2].floor(x), PALINDROME_SETS[x % 2].ceiling(x) - x)
                    / 2)
        .asLongStream()
        .sum();
  }
}