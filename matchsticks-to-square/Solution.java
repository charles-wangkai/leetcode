import java.util.Arrays;

class Solution {
  public boolean makesquare(int[] matchsticks) {
    Arrays.sort(matchsticks);

    int perimeter = Arrays.stream(matchsticks).sum();
    if (perimeter % 4 != 0) {
      return false;
    }
    int size = perimeter / 4;

    return search(matchsticks, size, new boolean[matchsticks.length], 0, 0, matchsticks.length - 1);
  }

  boolean search(int[] matchsticks, int size, boolean[] used, int side, int length, int index) {
    if (side == 3) {
      return true;
    }
    if (index == -1) {
      return false;
    }

    if (search(matchsticks, size, used, side, length, index - 1)) {
      return true;
    }

    if (!used[index] && length + matchsticks[index] <= size) {
      int nextSide;
      int nextLength;
      int nextIndex;
      if (length + matchsticks[index] == size) {
        nextSide = side + 1;
        nextLength = 0;
        nextIndex = matchsticks.length - 1;
      } else {
        nextSide = side;
        nextLength = length + matchsticks[index];
        nextIndex = index - 1;
      }

      used[index] = true;
      if (search(matchsticks, size, used, nextSide, nextLength, nextIndex)) {
        return true;
      }
      used[index] = false;
    }

    return false;
  }
}
