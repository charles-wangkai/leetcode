import java.util.Arrays;

class Solution {
  public int chalkReplacer(int[] chalk, int k) {
    k %= Arrays.stream(chalk).asLongStream().sum();

    for (int i = 0; ; ++i) {
      if (chalk[i] > k) {
        return i;
      }

      k -= chalk[i];
    }
  }
}
