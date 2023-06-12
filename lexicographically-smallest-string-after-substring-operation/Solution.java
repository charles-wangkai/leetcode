import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String smallestString(String s) {
    int[] aIndices = IntStream.range(0, s.length()).filter(i -> s.charAt(i) == 'a').toArray();
    for (int i = 0; i <= aIndices.length; ++i) {
      int beginIndex = ((i == 0) ? -1 : aIndices[i - 1]) + 1;
      int endIndex = ((i == aIndices.length) ? s.length() : aIndices[i]) - 1;
      if (beginIndex <= endIndex) {
        return operate(s, beginIndex, endIndex);
      }
    }

    return operate(s, s.length() - 1, s.length() - 1);
  }

  String operate(String s, int beginIndex, int endIndex) {
    return s.substring(0, beginIndex)
        + IntStream.rangeClosed(beginIndex, endIndex)
            .mapToObj(i -> (char) (Math.floorMod(s.charAt(i) - 'a' - 1, 26) + 'a'))
            .map(String::valueOf)
            .collect(Collectors.joining())
        + s.substring(endIndex + 1);
  }
}
