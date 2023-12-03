import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  static final int MODULUS = 1_000_000_007;
  static final int LIMIT = 100000;
  static int[] FACTORIALS = buildFactorials();
  static int[] FACTORIAL_INVS =
      Arrays.stream(FACTORIALS)
          .map(x -> BigInteger.valueOf(x).modInverse(BigInteger.valueOf(MODULUS)).intValue())
          .toArray();
  static int[] TWO_POWERS = buildTwoPowers();

  public int numberOfSequence(int n, int[] sick) {
    List<Segment> segments = new ArrayList<>();
    for (int i = 0; i <= sick.length; ++i) {
      int length = ((i == sick.length) ? n : sick[i]) - ((i == 0) ? -1 : sick[i - 1]) - 1;
      if (length != 0) {
        segments.add(new Segment(length, i == 0 || i == sick.length));
      }
    }

    int result = 1;
    int rest = segments.stream().mapToInt(Segment::length).sum();
    for (Segment segment : segments) {
      result = multiplyMod(result, CMod(rest, segment.length()));
      rest -= segment.length();
    }
    for (Segment segment : segments) {
      if (!segment.singleEnded()) {
        result = multiplyMod(result, TWO_POWERS[segment.length() - 1]);
      }
    }

    return result;
  }

  int CMod(int n, int r) {
    return multiplyMod(multiplyMod(FACTORIALS[n], FACTORIAL_INVS[r]), FACTORIAL_INVS[n - r]);
  }

  static int[] buildTwoPowers() {
    int[] twoPowers = new int[LIMIT + 1];
    twoPowers[0] = 1;
    for (int i = 1; i < twoPowers.length; ++i) {
      twoPowers[i] = multiplyMod(twoPowers[i - 1], 2);
    }

    return twoPowers;
  }

  static int[] buildFactorials() {
    int[] factorials = new int[LIMIT + 1];
    factorials[0] = 1;
    for (int i = 1; i < factorials.length; ++i) {
      factorials[i] = multiplyMod(factorials[i - 1], i);
    }

    return factorials;
  }

  static int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}

record Segment(int length, boolean singleEnded) {}
