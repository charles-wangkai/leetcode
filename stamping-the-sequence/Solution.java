import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
  public int[] movesToStamp(String stamp, String target) {
    List<Integer> indices = new ArrayList<>();
    int rest = target.length();
    char[] letters = target.toCharArray();
    while (rest != 0) {
      boolean found = false;
      for (int i = 0; i <= letters.length - stamp.length(); ++i) {
        int matchNum = match(letters, stamp, i);
        if (matchNum > 0) {
          indices.add(i);
          rest -= matchNum;
          found = true;

          break;
        }
      }

      if (!found) {
        return new int[0];
      }
    }
    Collections.reverse(indices);

    return indices.stream().mapToInt(x -> x).toArray();
  }

  int match(char[] letters, String stamp, int beginIndex) {
    int matchNum = 0;
    for (int i = 0; i < stamp.length(); ++i) {
      if (letters[beginIndex + i] != '*') {
        if (letters[beginIndex + i] != stamp.charAt(i)) {
          return -1;
        }

        ++matchNum;
      }
    }

    for (int i = 0; i < stamp.length(); ++i) {
      letters[beginIndex + i] = '*';
    }

    return matchNum;
  }
}
