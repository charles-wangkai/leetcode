import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int subsequencePairCount(int[] nums) {
    Map<State, Integer> dp = Map.of(new State(-1, -1), 1);
    for (int num : nums) {
      Map<State, Integer> nextDp = new HashMap<>(dp);
      for (State state : dp.keySet()) {
        for (State nextState :
            new State[] {
              new State((state.g1() == -1) ? num : gcd(state.g1(), num), state.g2()),
              new State(state.g1(), (state.g2() == -1) ? num : gcd(state.g2(), num))
            }) {
          nextDp.put(nextState, MOD_INT.addMod(nextDp.getOrDefault(nextState, 0), dp.get(state)));
        }
      }

      dp = nextDp;
    }

    return dp.keySet().stream()
        .filter(state -> state.g1() != -1 && state.g1() == state.g2())
        .mapToInt(dp::get)
        .reduce(0, MOD_INT::addMod);
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}

record State(int g1, int g2) {}

class ModInt {
  int modulus;

  ModInt(int modulus) {
    this.modulus = modulus;
  }

  int mod(long x) {
    return Math.floorMod(x, modulus);
  }

  int modInv(int x) {
    return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(modulus)).intValue();
  }

  int addMod(int x, int y) {
    return mod(x + y);
  }

  int multiplyMod(int x, int y) {
    return mod((long) x * y);
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, modInv(y));
  }

  int powMod(int base, long exponent) {
    if (exponent == 0) {
      return 1;
    }

    return multiplyMod(
        (exponent % 2 == 0) ? 1 : base, powMod(multiplyMod(base, base), exponent / 2));
  }
}
