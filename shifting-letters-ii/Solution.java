import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String shiftingLetters(String s, int[][] shifts) {
    int[] deltas = new int[s.length() + 1];
    for (int[] shift : shifts) {
      if (shift[2] == 1) {
        ++deltas[shift[0]];
        --deltas[shift[1] + 1];
      } else {
        --deltas[shift[0]];
        ++deltas[shift[1] + 1];
      }
    }

    int[] diffs = new int[s.length()];
    int diff = 0;
    for (int i = 0; i < diffs.length; ++i) {
      diff += deltas[i];
      diffs[i] = diff;
    }

    return IntStream.range(0, s.length())
        .mapToObj(i -> (char) (Math.floorMod(s.charAt(i) - 'a' + diffs[i], 26) + 'a'))
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}