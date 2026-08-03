import java.util.Arrays;

class Solution {
  public long minInitialStrength(int[] monsters, int[][] boosts) {
    long[] deltas = new long[monsters.length + 1];
    for (int[] boost : boosts) {
      deltas[boost[0]] += boost[2];
      deltas[boost[1] + 1] -= boost[2];
    }

    long[] bonuses = new long[monsters.length];
    for (int i = 0; i < bonuses.length; ++i) {
      bonuses[i] = ((i == 0) ? 0 : bonuses[i - 1]) + deltas[i];
    }

    long result = -1;
    long lower = 0;
    long upper = Arrays.stream(monsters).asLongStream().sum();
    while (lower <= upper) {
      long middle = (lower + upper) / 2;
      if (check(monsters, bonuses, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] monsters, long[] bonuses, long strength) {
    for (int i = 0; i < monsters.length; ++i) {
      if (strength + bonuses[i] < monsters[i]) {
        return false;
      }

      strength = Math.max(0, strength - monsters[i]);
    }

    return true;
  }
}