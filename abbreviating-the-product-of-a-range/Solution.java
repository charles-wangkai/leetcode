class Solution {
  static final long PRECISION_LIMIT = 1_000_000_000_000L;
  static final int MODULUS = 100000;

  public String abbreviateProduct(int left, int right) {
    String precise = computePrecise(left, right);

    return (precise != null) ? precise : computeApproximate(left, right);
  }

  String computePrecise(int left, int right) {
    long rest = 1;
    int exponent = 0;
    for (int i = left; i <= right; ++i) {
      rest *= i;
      while (rest % 10 == 0) {
        rest /= 10;
        ++exponent;
      }

      if (rest >= PRECISION_LIMIT) {
        return null;
      }
    }

    return (String.valueOf(rest).length() <= 10) ? String.format("%de%d", rest, exponent) : null;
  }

  String computeApproximate(int left, int right) {
    int suffix = 1;
    int count2 = 0;
    int count5 = 0;
    for (int i = left; i <= right; ++i) {
      int factor = i;
      while (factor % 2 == 0) {
        factor /= 2;
        ++count2;
      }
      while (factor % 5 == 0) {
        factor /= 5;
        ++count5;
      }

      suffix = multiplyMod(suffix, factor);
    }

    int exponent = Math.min(count2, count5);
    for (int i = 0; i < count2 - exponent; ++i) {
      suffix = multiplyMod(suffix, 2);
    }
    for (int i = 0; i < count5 - exponent; ++i) {
      suffix = multiplyMod(suffix, 5);
    }

    double product = 1;
    for (int i = left; i <= right; ++i) {
      product *= i;
      while (product >= 10) {
        product /= 10;
      }
    }
    int prefix = (int) Math.floor(product * 10000);

    return String.format("%d...%05de%d", prefix, suffix, exponent);
  }

  int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }
}