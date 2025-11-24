import java.util.Arrays;

class Solution {
  public int[] lexSmallestNegatedPerm(int n, long target) {
    if (Math.abs(target) % 2 != n * (n + 1L) / 2 % 2 || Math.abs(target) > n * (n + 1L) / 2) {
      return new int[0];
    }

    int[] result = new int[n];
    for (int i = result.length - 1; i >= 0; --i) {
      for (int value : new int[] {-(i + 1), i + 1}) {
        long nextTarget = target - value;
        if (Math.abs(nextTarget) <= i * (i + 1L) / 2) {
          result[i] = value;
          target -= value;

          break;
        }
      }
    }
    Arrays.sort(result);

    return result;
  }
}