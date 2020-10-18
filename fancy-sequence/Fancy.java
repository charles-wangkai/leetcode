import java.math.BigInteger;

class Fancy {
  static final int MODULUS = 1_000_000_007;
  static final int LIMIT = 100000;

  int length = 0;
  int base = 0;
  int[] diffs = new int[LIMIT];
  int[] factors = new int[LIMIT];
  int[] prefixProducts = new int[LIMIT + 1];

  public Fancy() {
    prefixProducts[0] = 1;
  }

  public void append(int val) {
    ++length;
    diffs[length - 1] = subtractMod(val, base);
    factors[length - 1] = 1;
    prefixProducts[length] = prefixProducts[length - 1];
  }

  public void addAll(int inc) {
    base = addMod(base, inc);
  }

  public void multAll(int m) {
    base = multiplyMod(base, m);

    factors[length - 1] = multiplyMod(factors[length - 1], m);
    prefixProducts[length] = multiplyMod(prefixProducts[length], m);
  }

  public int getIndex(int idx) {
    if (idx >= length) {
      return -1;
    }

    return addMod(
        base, multiplyMod(diffs[idx], divideMod(prefixProducts[length], prefixProducts[idx])));
  }

  private int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  private int subtractMod(int x, int y) {
    return (x - y + MODULUS) % MODULUS;
  }

  private int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }

  private int divideMod(int x, int y) {
    return multiplyMod(x, BigInteger.valueOf(y).modInverse(BigInteger.valueOf(MODULUS)).intValue());
  }
}

// Your Fancy object will be instantiated and called as such:
// Fancy obj = new Fancy();
// obj.append(val);
// obj.addAll(inc);
// obj.multAll(m);
// int param_4 = obj.getIndex(idx);
