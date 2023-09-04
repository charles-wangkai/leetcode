import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  static final int ALPHABET_SIZE = 26;
  static final int MODULUS = 1_000_000_007;

  public int countKSubsequencesWithMaxBeauty(String s, int k) {
    if (k > ALPHABET_SIZE) {
      return 0;
    }

    int[] counts = new int[ALPHABET_SIZE];
    for (char c : s.toCharArray()) {
      ++counts[c - 'a'];
    }

    int[] sortedCounts =
        Arrays.stream(counts)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

    int lastCount = sortedCounts[k - 1];
    int result =
        C(
            (int) Arrays.stream(sortedCounts).filter(count -> count == lastCount).count(),
            (int) IntStream.range(0, k).filter(i -> sortedCounts[i] == lastCount).count());
    for (int i = 0; i < k; ++i) {
      result = multiplyMod(result, sortedCounts[i]);
    }

    return result;
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int C(int n, int r) {
    int result = 1;
    for (int i = 0; i < r; ++i) {
      result = result * (n - i) / (i + 1);
    }

    return result;
  }
}
