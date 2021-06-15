import java.util.Arrays;

class Solution {
  public boolean makesquare(int[] matchsticks) {
    Arrays.sort(matchsticks);

    long perimeter = Arrays.stream(matchsticks).asLongStream().sum();
    if (perimeter % 4 != 0) {
      return false;
    }
    long size = perimeter / 4;

    return search(matchsticks, size, new boolean[matchsticks.length], 0, 0, matchsticks.length - 1);
  }

  boolean search(int[] matchsticks, long size, boolean[] used, int side, int length, int index) {
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
      int nextSide = side;
      int nextLength = length + matchsticks[index];
      int nextIndex = index - 1;
      if (nextLength == size) {
        ++nextSide;
        nextLength = 0;
        nextIndex = matchsticks.length - 1;
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
