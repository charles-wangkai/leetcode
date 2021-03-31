import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int[] movesToStamp(String stamp, String target) {
    List<Integer> indices = new ArrayList<>();
    int remainNum = target.length();
    StringBuilder sb = new StringBuilder(target);
    while (remainNum != 0) {
      boolean found = false;
      for (int i = 0; i <= sb.length() - stamp.length(); ++i) {
        int matchNum = match(sb, stamp, i);
        if (matchNum > 0) {
          indices.add(i);
          remainNum -= matchNum;
          found = true;

          break;
        }
      }

      if (!found) {
        return new int[0];
      }
    }

    return IntStream.range(0, indices.size())
        .map(i -> indices.get(indices.size() - 1 - i))
        .toArray();
  }

  int match(StringBuilder sb, String stamp, int beginIndex) {
    int matchNum = 0;
    for (int i = 0; i < stamp.length(); ++i) {
      char stampCh = stamp.charAt(i);
      char targetCh = sb.charAt(beginIndex + i);

      if (targetCh != '*') {
        if (targetCh != stampCh) {
          return -1;
        }

        ++matchNum;
      }
    }

    for (int i = 0; i < stamp.length(); ++i) {
      sb.setCharAt(beginIndex + i, '*');
    }

    return matchNum;
  }
}
