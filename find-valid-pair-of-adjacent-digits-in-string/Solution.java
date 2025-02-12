import java.util.stream.IntStream;

class Solution {
  public String findValidPair(String s) {
    int[] counts = new int[10];
    for (char c : s.toCharArray()) {
      ++counts[c - '0'];
    }

    return IntStream.range(0, s.length() - 1)
        .filter(
            i -> {
              int digit1 = s.charAt(i) - '0';
              int digit2 = s.charAt(i + 1) - '0';

              return digit1 != digit2 && counts[digit1] == digit1 && counts[digit2] == digit2;
            })
        .min()
        .stream()
        .mapToObj(i -> s.substring(i, i + 2))
        .findAny()
        .orElse("");
  }
}