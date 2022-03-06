import java.util.Arrays;

class Solution {
  public long minimalKSum(int[] nums, int k) {
    int[] sorted = Arrays.stream(nums).boxed().distinct().sorted().mapToInt(x -> x).toArray();

    long result = k * (k + 1L) / 2;
    int max = k;
    for (int x : sorted) {
      if (x > max) {
        break;
      }

      result += max + 1 - x;
      ++max;
    }

    return result;
  }
}