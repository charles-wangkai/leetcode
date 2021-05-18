import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int minSwaps(String s) {
    int count0 = (int) s.chars().filter(ch -> ch == '0').count();
    int count1 = s.length() - count0;

    int result = Integer.MAX_VALUE;
    if (count0 == count1 || count0 - count1 == 1) {
      result = Math.min(result, computeDiffNum(s, buildAlternating(s.length(), 0)) / 2);
    }
    if (count0 == count1 || count1 - count0 == 1) {
      result = Math.min(result, computeDiffNum(s, buildAlternating(s.length(), 1)) / 2);
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }

  String buildAlternating(int length, int start) {
    return IntStream.range(0, length)
        .mapToObj(i -> String.valueOf((i % 2 == 0) ? start : (1 - start)))
        .collect(Collectors.joining());
  }

  int computeDiffNum(String s1, String s2) {
    return (int) IntStream.range(0, s1.length()).filter(i -> s1.charAt(i) != s2.charAt(i)).count();
  }
}
