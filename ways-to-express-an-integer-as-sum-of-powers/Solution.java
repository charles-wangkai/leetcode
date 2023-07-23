import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfWays(int n, int x) {
    Map<Integer, Integer> powerSumToWayNum = Map.of(0, 1);
    for (int i = 1; ; ++i) {
      int i_ = i;
      int power = IntStream.range(0, x).reduce(1, (acc, e) -> acc * i_);
      if (power > n) {
        break;
      }

      Map<Integer, Integer> nextPowerSumToWayNum = new HashMap<>(powerSumToWayNum);
      for (int powerSum : powerSumToWayNum.keySet()) {
        int nextPowerSum = powerSum + power;
        if (nextPowerSum <= n) {
          nextPowerSumToWayNum.put(
              nextPowerSum,
              addMod(
                  nextPowerSumToWayNum.getOrDefault(nextPowerSum, 0),
                  powerSumToWayNum.get(powerSum)));
        }
      }

      powerSumToWayNum = nextPowerSumToWayNum;
    }

    return powerSumToWayNum.getOrDefault(n, 0);
  }

  int addMod(int a, int b) {
    return Math.floorMod(a + b, MODULUS);
  }
}
