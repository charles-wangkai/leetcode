// https://leetcode.com/problems/minimum-operations-to-make-a-rotated-palindrome-ii/solutions/8465873/os-nlogn-fft-convolution-by-david_hoengy-wf1u/

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 998244353;

  public int minOperations(String s) {
    int n = s.length();

    String t = s + s;

    Character[] letters = s.chars().distinct().mapToObj(c -> (char) c).toArray(Character[]::new);

    int[] costs = new int[3 * n];
    for (char letter : letters) {
      int[] sConv = computeConvolution(s, letter);
      int[] tConv = computeConvolution(t, letter);

      for (int i = n - 1; i < n - 1 + 2 * n; i += 2) {
        costs[i] += tConv[i] - ((i >= n) ? sConv[i - n] : 0);
      }
    }

    return IntStream.range(0, n)
        .map(rotateNum -> costs[n - 1 + 2 * rotateNum] + rotateNum)
        .min()
        .getAsInt();
  }

  int[] computeConvolution(String str, char letter) {
    int[] P = str.chars().map(c -> (c == letter) ? 1 : 0).toArray();
    int[] Q =
        str.chars()
            .map(c -> (c > letter) ? Math.min((c - letter) % 26, Math.floorMod(letter - c, 26)) : 0)
            .toArray();

    return Arrays.stream(
            Convolution.convolution(
                Arrays.stream(P).asLongStream().toArray(),
                Arrays.stream(Q).asLongStream().toArray(),
                MODULUS))
        .mapToInt(Math::toIntExact)
        .toArray();
  }
}

class Convolution {
  private static final long MOD1 = 754974721L;
  private static final long MOD2 = 167772161L;
  private static final long MOD3 = 469762049L;

  private static final int ROOT1 = 11;
  private static final int ROOT2 = 3;
  private static final int ROOT3 = 3;

  private static final long M2M3 = MOD2 * MOD3;
  private static final long M1M3 = MOD1 * MOD3;
  private static final long M1M2 = MOD1 * MOD2;

  private static final long M1M2M3 = MOD1 * MOD2 * MOD3;

  private static final long I1 = new ModInt((int) MOD1).powMod((int) (M2M3 % MOD1), MOD1 - 2);
  private static final long I2 = new ModInt((int) MOD2).powMod((int) (M1M3 % MOD2), MOD2 - 2);
  private static final long I3 = new ModInt((int) MOD3).powMod((int) (M1M2 % MOD3), MOD3 - 2);

  // https://github.com/ocha98/ac-library-java/blob/main/src/ac_library/Convolution.java
  public static long[] convolution(long[] a, long[] b, int mod) {
    ModInt modInt = new ModInt(mod);

    int n = a.length;
    int m = b.length;
    if (n == 0 || m == 0) return new long[0];

    int z = 1 << ceilPow2(n + m - 1);
    {
      long[] na = new long[z];
      long[] nb = new long[z];
      System.arraycopy(a, 0, na, 0, n);
      System.arraycopy(b, 0, nb, 0, m);
      a = na;
      b = nb;
    }

    int g = primitiveRoot(mod);

    butterfly(a, g, mod);
    butterfly(b, g, mod);
    for (int i = 0; i < z; i++) {
      a[i] = a[i] * b[i] % mod;
    }
    butterflyInv(a, g, mod);
    a = java.util.Arrays.copyOf(a, n + m - 1);

    long iz = modInt.powMod(z, mod - 2);
    for (int i = 0; i < n + m - 1; i++) a[i] = a[i] * iz % mod;
    return a;
  }

  private static int ceilPow2(int n) {
    int x = 0;
    while ((1L << x) < n) x++;
    return x;
  }

  private static int primitiveRoot(int m) {
    ModInt modInt = new ModInt(m);

    if (m == 2) return 1;
    if (m == 167772161) return 3;
    if (m == 469762049) return 3;
    if (m == 754974721) return 11;
    if (m == 998244353) return 3;

    int[] divs = new int[20];
    divs[0] = 2;
    int cnt = 1;
    int x = (m - 1) / 2;
    while (x % 2 == 0) x /= 2;
    for (int i = 3; (long) (i) * i <= x; i += 2) {
      if (x % i == 0) {
        divs[cnt++] = i;
        while (x % i == 0) {
          x /= i;
        }
      }
    }
    if (x > 1) {
      divs[cnt++] = x;
    }
    for (int g = 2; ; g++) {
      boolean ok = true;
      for (int i = 0; i < cnt; i++) {
        if (modInt.powMod(g, (m - 1) / divs[i]) == 1) {
          ok = false;
          break;
        }
      }
      if (ok) return g;
    }
  }

  private static void butterfly(long[] a, int g, int mod) {
    int n = a.length;
    int h = ceilPow2(n);

    FftInfo info = new FftInfo(g, mod);

    int len = 0; // a[i, i+(n>>len), i+2*(n>>len), ..] is transformed
    while (len < h) {
      if (h - len == 1) {
        int p = 1 << (h - len - 1);
        long rot = 1;
        for (int s = 0; s < (1 << len); s++) {
          int offset = s << (h - len);
          for (int i = 0; i < p; i++) {
            long l = a[i + offset];
            long r = a[i + offset + p] * rot % mod;
            a[i + offset] = (l + r) % mod;
            a[i + offset + p] = (l + mod - r) % mod;
          }
          if (s + 1 != (1 << len)) {
            rot *= info.rate2[Integer.numberOfTrailingZeros(~s)];
            rot %= mod;
          }
        }
        len++;
      } else {
        // 4-base
        int p = 1 << (h - len - 2);
        long rot = 1, imag = info.root[2];
        for (int s = 0; s < (1 << len); s++) {
          long rot2 = rot * rot % mod;
          long rot3 = rot2 * rot % mod;
          int offset = s << (h - len);
          for (int i = 0; i < p; i++) {
            long mod2 = 1L * mod * mod;
            long a0 = 1L * a[i + offset];
            long a1 = 1L * a[i + offset + p] * rot % mod;
            long a2 = 1L * a[i + offset + 2 * p] * rot2 % mod;
            long a3 = 1L * a[i + offset + 3 * p] * rot3 % mod;
            long a1na3imag = 1L * (a1 + mod2 - a3) % mod * imag % mod;
            long na2 = mod2 - a2;
            a[i + offset] = (a0 + a2 + a1 + a3) % mod;
            a[i + offset + 1 * p] = (a0 + a2 + (2 * mod2 - (a1 + a3))) % mod;
            a[i + offset + 2 * p] = (a0 + na2 + a1na3imag) % mod;
            a[i + offset + 3 * p] = (a0 + na2 + (mod2 - a1na3imag)) % mod;
          }
          if (s + 1 != (1 << len)) {
            rot *= info.rate3[Integer.numberOfTrailingZeros(~s)];
            rot %= mod;
          }
        }
        len += 2;
      }
    }
  }

  private static void butterflyInv(long[] a, int g, int mod) {
    int n = a.length;
    int h = ceilPow2(n);

    FftInfo info = new FftInfo(g, mod);

    int len = h; // a[i, i+(n>>len), i+2*(n>>len), ..] is transformed
    while (len > 0) {
      if (len == 1) {
        int p = 1 << (h - len);
        long irot = 1;
        for (int s = 0; s < (1 << (len - 1)); s++) {
          int offset = s << (h - len + 1);
          for (int i = 0; i < p; i++) {
            long l = a[i + offset];
            long r = a[i + offset + p];
            a[i + offset] = (l + r) % mod;
            a[i + offset + p] = (mod + l - r) % mod * irot % mod;
          }
          if (s + 1 != (1 << (len - 1))) {
            irot *= info.irate2[Integer.numberOfTrailingZeros(~s)];
            irot %= mod;
          }
        }
        len--;
      } else {
        // 4-base
        int p = 1 << (h - len);
        long irot = 1, iimag = info.iroot[2];
        for (int s = 0; s < (1 << (len - 2)); s++) {
          long irot2 = irot * irot % mod;
          long irot3 = irot2 * irot % mod;
          int offset = s << (h - len + 2);
          for (int i = 0; i < p; i++) {
            long a0 = 1L * a[i + offset + 0 * p];
            long a1 = 1L * a[i + offset + 1 * p];
            long a2 = 1L * a[i + offset + 2 * p];
            long a3 = 1L * a[i + offset + 3 * p];

            long a2na3iimag = 1L * (mod + a2 - a3) % mod * iimag % mod;

            a[i + offset] = (a0 + a1 + a2 + a3) % mod;
            a[i + offset + 1 * p] = (a0 + (mod - a1) + a2na3iimag) % mod * irot % mod;
            a[i + offset + 2 * p] = (a0 + a1 + (mod - a2) + (mod - a3)) % mod * irot2 % mod;
            a[i + offset + 3 * p] = (a0 + (mod - a1) + (mod - a2na3iimag)) % mod * irot3 % mod;
          }
          if (s + 1 != (1 << (len - 2))) {
            irot *= info.irate3[Integer.numberOfTrailingZeros(~s)];
            irot %= mod;
          }
        }
        len -= 2;
      }
    }
  }

  static class FftInfo {
    private static int bsfConstexpr(int n) {
      int x = 0;
      while ((n & (1 << x)) == 0) x++;
      return x;
    }

    private static long inv(long a, long mod) {
      long b = mod;
      long p = 1, q = 0;
      while (b > 0) {
        long c = a / b;
        long d;
        d = a;
        a = b;
        b = d % b;
        d = p;
        p = q;
        q = d - c * q;
      }
      return p < 0 ? p + mod : p;
    }

    private final int rank2;
    public final long[] root;
    public final long[] iroot;
    public final long[] rate2;
    public final long[] irate2;
    public final long[] rate3;
    public final long[] irate3;

    public FftInfo(int g, int mod) {
      ModInt modInt = new ModInt(mod);

      rank2 = bsfConstexpr(mod - 1);
      root = new long[rank2 + 1];
      iroot = new long[rank2 + 1];
      rate2 = new long[Math.max(0, rank2 - 2 + 1)];
      irate2 = new long[Math.max(0, rank2 - 2 + 1)];
      rate3 = new long[Math.max(0, rank2 - 3 + 1)];
      irate3 = new long[Math.max(0, rank2 - 3 + 1)];

      root[rank2] = modInt.powMod(g, (mod - 1) >> rank2);
      iroot[rank2] = inv(root[rank2], mod);
      for (int i = rank2 - 1; i >= 0; i--) {
        root[i] = root[i + 1] * root[i + 1] % mod;
        iroot[i] = iroot[i + 1] * iroot[i + 1] % mod;
      }

      {
        long prod = 1, iprod = 1;
        for (int i = 0; i <= rank2 - 2; i++) {
          rate2[i] = root[i + 2] * prod % mod;
          irate2[i] = iroot[i + 2] * iprod % mod;
          prod = prod * iroot[i + 2] % mod;
          iprod = iprod * root[i + 2] % mod;
        }
      }
      {
        long prod = 1, iprod = 1;
        for (int i = 0; i <= rank2 - 3; i++) {
          rate3[i] = root[i + 3] * prod % mod;
          irate3[i] = iroot[i + 3] * iprod % mod;
          prod = prod * iroot[i + 3] % mod;
          iprod = iprod * root[i + 3] % mod;
        }
      }
    }
  }

  // https://chatgpt.com/share/6aed8f33-368c-83ec-a294-887a58c0ba59
  public static long[] convolutionLL(long[] a, long[] b) {
    int n = a.length;
    int m = b.length;

    if (n == 0 || m == 0) {
      return new long[0];
    }

    long resultLength = (long) n + m - 1;

    int len = (int) resultLength;

    int[] c1 = convolutionMod(a, b, MOD1, ROOT1);
    int[] c2 = convolutionMod(a, b, MOD2, ROOT2);
    int[] c3 = convolutionMod(a, b, MOD3, ROOT3);

    long[] result = new long[len];

    for (int i = 0; i < len; i++) {
      long x = 0;

      x += ((long) c1[i] * I1 % MOD1) * M2M3;
      x += ((long) c2[i] * I2 % MOD2) * M1M3;
      x += ((long) c3[i] * I3 % MOD3) * M1M2;

      long diff = c1[i] - Math.floorMod(x, MOD1);
      if (diff < 0) {
        diff += MOD1;
      }

      long[] offset = {0, 0, M1M2M3, 2 * M1M2M3, 3 * M1M2M3};

      x -= offset[(int) (diff % 5)];

      result[i] = x;
    }

    return result;
  }

  private static int[] convolutionMod(long[] a, long[] b, long mod, int primitiveRoot) {
    int n = a.length;
    int m = b.length;
    int resultLength = n + m - 1;

    int z = 1;
    while (z < resultLength) {
      z <<= 1;
    }

    int[] fa = new int[z];
    int[] fb = new int[z];

    for (int i = 0; i < n; i++) {
      fa[i] = (int) Math.floorMod(a[i], mod);
    }

    for (int i = 0; i < m; i++) {
      fb[i] = (int) Math.floorMod(b[i], mod);
    }

    ntt(fa, false, mod, primitiveRoot);
    ntt(fb, false, mod, primitiveRoot);

    for (int i = 0; i < z; i++) {
      fa[i] = (int) ((long) fa[i] * fb[i] % mod);
    }

    ntt(fa, true, mod, primitiveRoot);

    return Arrays.copyOf(fa, resultLength);
  }

  private static void ntt(int[] a, boolean inverse, long mod, int primitiveRoot) {
    ModInt modInt = new ModInt((int) mod);

    int n = a.length;

    for (int i = 1, j = 0; i < n; i++) {
      int bit = n >> 1;

      while ((j & bit) != 0) {
        j ^= bit;
        bit >>= 1;
      }

      j ^= bit;

      if (i < j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
      }
    }

    for (int len = 2; len <= n; len <<= 1) {
      long wlen = modInt.powMod(primitiveRoot, (mod - 1) / len);

      if (inverse) {
        wlen = modInt.powMod((int) wlen, mod - 2);
      }

      int half = len >> 1;

      for (int i = 0; i < n; i += len) {
        long w = 1;

        for (int j = 0; j < half; j++) {
          long u = a[i + j];
          long v = (long) a[i + j + half] * w % mod;

          long sum = u + v;
          if (sum >= mod) {
            sum -= mod;
          }

          long diff = u - v;
          if (diff < 0) {
            diff += mod;
          }

          a[i + j] = (int) sum;
          a[i + j + half] = (int) diff;

          w = w * wlen % mod;
        }
      }
    }

    if (inverse) {
      long invN = modInt.powMod(n, mod - 2);

      for (int i = 0; i < n; i++) {
        a[i] = (int) (a[i] * invN % mod);
      }
    }
  }
}

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
