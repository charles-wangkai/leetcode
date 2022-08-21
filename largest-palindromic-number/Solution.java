import java.util.OptionalInt;
import java.util.stream.IntStream;

class Solution {
  public String largestPalindromic(String num) {
    int[] counts = new int[10];
    for (char c : num.toCharArray()) {
      ++counts[c - '0'];
    }

    StringBuilder half = new StringBuilder();
    for (int i = counts.length - 1; i >= 0; --i) {
      if (!half.isEmpty() || i != 0) {
        half.append(String.valueOf(i).repeat(counts[i] / 2));
      }
    }

    OptionalInt middle = IntStream.range(0, counts.length).filter(i -> counts[i] % 2 != 0).max();

    String result =
        String.format(
            "%s%s%s",
            half.toString(),
            middle.isPresent() ? String.valueOf(middle.getAsInt()) : "",
            half.reverse().toString());
    if (result.isEmpty()) {
      result = "0";
    }

    return result;
  }
}