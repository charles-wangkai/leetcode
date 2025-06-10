import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public long maxGCDScore(int[] nums, int k) {
    int n = nums.length;

    int[] rests = nums.clone();
    int[] twoPowers = new int[n];
    Arrays.fill(twoPowers, 1);
    for (int i = 0; i < n; ++i) {
      while (rests[i] % 2 == 0) {
        rests[i] /= 2;
        twoPowers[i] *= 2;
      }
    }

    long result = 0;
    for (int beginIndex = 0; beginIndex < n; ++beginIndex) {
      int g = rests[beginIndex];
      SortedMap<Integer, Integer> twoPowerToCount = new TreeMap<>();
      for (int endIndex = beginIndex; endIndex < n; ++endIndex) {
        g = gcd(g, rests[endIndex]);
        twoPowerToCount.put(
            twoPowers[endIndex], twoPowerToCount.getOrDefault(twoPowers[endIndex], 0) + 1);

        result =
            Math.max(
                result,
                (endIndex - beginIndex + 1L)
                    * g
                    * twoPowerToCount.firstKey()
                    * ((twoPowerToCount.get(twoPowerToCount.firstKey()) <= k) ? 2 : 1));
      }
    }

    return result;
  }

  static int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}