import java.util.stream.IntStream;

class Solution {
  public int nextBeautifulNumber(int n) {
    return IntStream.iterate(n + 1, x -> x + 1).filter(this::isBalanced).findFirst().getAsInt();
  }

  boolean isBalanced(int x) {
    int[] counts = new int[10];
    for (char c : String.valueOf(x).toCharArray()) {
      ++counts[c - '0'];
    }

    return IntStream.range(0, counts.length).allMatch(i -> counts[i] == 0 || counts[i] == i);
  }
}
