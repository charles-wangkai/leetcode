import java.util.stream.IntStream;

class Solution {
  public int nextBeautifulNumber(int n) {
    for (int i = n + 1; ; ++i) {
      if (check(i)) {
        return i;
      }
    }
  }

  boolean check(int x) {
    int[] counts = new int[10];
    for (char ch : String.valueOf(x).toCharArray()) {
      ++counts[ch - '0'];
    }

    return IntStream.range(0, counts.length).allMatch(i -> counts[i] == 0 || counts[i] == i);
  }
}
