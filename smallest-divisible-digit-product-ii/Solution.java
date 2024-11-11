// https://leetcode.com/problems/smallest-divisible-digit-product-ii/solutions/6028394/python-brute-force/

class Solution {
  static final int[] PRIMES = {2, 3, 5, 7};

  public String smallestNumber(String num, long t) {
    if (!check(t)) {
      return "-1";
    }

    long[] requirements = new long[num.length() + 1];
    requirements[0] = t;
    for (int i = 1; i < requirements.length; ++i) {
      if (num.charAt(i - 1) == '0') {
        break;
      }

      requirements[i] = requirements[i - 1] / gcd(requirements[i - 1], num.charAt(i - 1) - '0');
    }

    int zeroIndex = num.indexOf('0');
    if (zeroIndex == -1 && requirements[requirements.length - 1] == 1) {
      return num;
    }

    for (int i = (zeroIndex == -1) ? (num.length() - 1) : zeroIndex; i >= 0; --i) {
      for (int d = num.charAt(i) - '0' + 1; d <= 9; ++d) {
        String ending = fill(requirements[i] / gcd(requirements[i], d), num.length() - 1 - i);
        if (ending.length() == num.length() - 1 - i) {
          return "%s%d%s".formatted(num.substring(0, i), d, ending);
        }
      }
    }

    return fill(t, num.length() + 1);
  }

  String fill(long requirement, int length) {
    StringBuilder sb = new StringBuilder();
    for (int d = 9; d >= 2; --d) {
      while (requirement % d == 0) {
        sb.append(d);
        requirement /= d;
      }
    }
    while (sb.length() < length) {
      sb.append(1);
    }

    return sb.reverse().toString();
  }

  long gcd(long x, long y) {
    return (y == 0) ? x : gcd(y, x % y);
  }

  boolean check(long t) {
    for (int prime : PRIMES) {
      while (t % prime == 0) {
        t /= prime;
      }
    }

    return t == 1;
  }
}