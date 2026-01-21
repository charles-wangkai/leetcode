import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int alternatingXOR(int[] nums, int target1, int target2) {
    Map<State, Integer> stateToPartitionNum = new HashMap<>();
    stateToPartitionNum.put(new State(0, target1), 1);
    stateToPartitionNum.put(new State(0, target2), 1);

    int suffixXor = 0;
    for (int i = nums.length - 1; i >= 1; --i) {
      suffixXor ^= nums[i];

      int suffixXor_ = suffixXor;
      Element[] elements =
          IntStream.of(target1, target2)
              .mapToObj(
                  target ->
                      new Element(
                          new State(suffixXor_, target),
                          stateToPartitionNum.getOrDefault(
                              new State(suffixXor_ ^ target, target1 + target2 - target), 0)))
              .toArray(Element[]::new);
      for (Element element : elements) {
        stateToPartitionNum.put(
            element.state(),
            MOD_INT.addMod(stateToPartitionNum.getOrDefault(element.state(), 0), element.delta()));
      }
    }

    suffixXor ^= nums[0];

    return stateToPartitionNum.getOrDefault(new State(suffixXor ^ target1, target2), 0);
  }
}

record State(int suffixXor, int startTarget) {}

record Element(State state, int delta) {}

class ModInt {
  int modulus;

  ModInt(int modulus) {
    this.modulus = modulus;
  }

  int mod(long x) {
    return (int) (x % modulus);
  }

  int modInv(int x) {
    return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(modulus)).intValue();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, modulus);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, modulus);
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
