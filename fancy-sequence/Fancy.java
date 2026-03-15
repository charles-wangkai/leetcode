import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class Fancy {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  int coefficient = 1;
  int constant;
  List<Integer> values = new ArrayList<>();

  public void append(int val) {
    values.add(MOD_INT.divideMod(MOD_INT.addMod(val, -constant), coefficient));
  }

  public void addAll(int inc) {
    constant = MOD_INT.addMod(constant, inc);
  }

  public void multAll(int m) {
    coefficient = MOD_INT.multiplyMod(coefficient, m);
    constant = MOD_INT.multiplyMod(constant, m);
  }

  public int getIndex(int idx) {
    return (idx < values.size())
        ? MOD_INT.addMod(MOD_INT.multiplyMod(values.get(idx), coefficient), constant)
        : -1;
  }
}

// Your Fancy object will be instantiated and called as such:
// Fancy obj = new Fancy();
// obj.append(val);
// obj.addAll(inc);
// obj.multAll(m);
// int param_4 = obj.getIndex(idx);

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
