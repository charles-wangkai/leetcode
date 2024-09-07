import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
  public int[] findPattern(int[][] board, String[] pattern) {
    int height = pattern.length;
    int width = pattern[0].length();

    for (int beginR = 0; beginR + height <= board.length; ++beginR) {
      for (int beginC = 0; beginC + width <= board[beginR].length; ++beginC) {
        if (isMatch(board, pattern, beginR, beginC)) {
          return new int[] {beginR, beginC};
        }
      }
    }

    return new int[] {-1, -1};
  }

  boolean isMatch(int[][] board, String[] pattern, int beginR, int beginC) {
    int height = pattern.length;
    int width = pattern[0].length();

    Map<Character, Integer> letterToDigit = new HashMap<>();
    Set<Integer> digitSeen = new HashSet<>();
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        char c = pattern[i].charAt(j);
        int digit = board[beginR + i][beginC + j];
        if (Character.isLetter(c)) {
          if (letterToDigit.containsKey(c)) {
            if (letterToDigit.get(c) != digit) {
              return false;
            }
          } else if (digitSeen.contains(digit)) {
            return false;
          }

          letterToDigit.put(c, digit);
          digitSeen.add(digit);
        } else if (c - '0' != digit) {
          return false;
        }
      }
    }

    return true;
  }
}