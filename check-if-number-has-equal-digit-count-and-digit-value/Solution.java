import java.util.stream.IntStream;

class Solution {
  public boolean digitCount(String num) {
    int[] counts = new int[num.length()];
    for (char c : num.toCharArray()) {
      if (c - '0' < counts.length) {
        ++counts[c - '0'];
      }
    }

    return IntStream.range(0, num.length()).allMatch(i -> counts[i] == num.charAt(i) - '0');
  }
}