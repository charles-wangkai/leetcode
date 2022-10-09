import java.util.stream.IntStream;

class Solution {
  public int[] findArray(int[] pref) {
    return IntStream.range(0, pref.length)
        .map(i -> pref[i] ^ ((i == 0) ? 0 : pref[i - 1]))
        .toArray();
  }
}
