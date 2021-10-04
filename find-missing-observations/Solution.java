import java.util.Arrays;

class Solution {
  public int[] missingRolls(int[] rolls, int mean, int n) {
    int total = mean * (rolls.length + n) - Arrays.stream(rolls).sum();
    if (total < n || total > n * 6) {
      return new int[0];
    }

    int[] result = new int[n];
    Arrays.fill(result, 1);
    int rest = total - n;
    for (int i = 0; i < result.length; ++i) {
      int delta = Math.min(5, rest);
      result[i] += delta;
      rest -= delta;
    }

    return result;
  }
}
