import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countTrapezoids(int[][] points) {
    Map<Integer, Integer> yToCount = new HashMap<>();
    for (int[] point : points) {
      yToCount.put(point[1], yToCount.getOrDefault(point[1], 0) + 1);
    }

    int[] wayNums =
        yToCount.values().stream().mapToInt(count -> mod(count * (count - 1L) / 2)).toArray();

    int wayNumSum = Arrays.stream(wayNums).reduce(this::addMod).getAsInt();

    return divideMod(
        addMod(
            multiplyMod(wayNumSum, wayNumSum),
            -Arrays.stream(wayNums)
                .map(wayNum -> multiplyMod(wayNum, wayNum))
                .reduce(this::addMod)
                .getAsInt()),
        2);
  }

  int mod(long x) {
    return (int) (x % MODULUS);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, BigInteger.valueOf(y).modInverse(BigInteger.valueOf(MODULUS)).intValue());
  }
}