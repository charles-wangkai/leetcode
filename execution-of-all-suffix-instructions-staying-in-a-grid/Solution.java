import java.util.stream.IntStream;

class Solution {
  public int[] executeInstructions(int n, int[] startPos, String s) {
    return IntStream.range(0, s.length()).map(i -> computeStepNum(n, startPos, s, i)).toArray();
  }

  int computeStepNum(int n, int[] startPos, String s, int beginIndex) {
    int result = 0;
    int r = startPos[0];
    int c = startPos[1];
    for (int i = beginIndex; i < s.length(); ++i) {
      char instruction = s.charAt(i);
      if (instruction == 'L') {
        --c;
      } else if (instruction == 'R') {
        ++c;
      } else if (instruction == 'U') {
        --r;
      } else {
        ++r;
      }

      if (!(r >= 0 && r < n && c >= 0 && c < n)) {
        break;
      }

      ++result;
    }

    return result;
  }
}