import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;

class Solution {
  public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
    Arrays.sort(tiles, Comparator.comparing(tile -> tile[0]));

    int result = 0;
    Deque<int[]> deque = new ArrayDeque<>();
    int index = 0;
    int sum = 0;
    while (!deque.isEmpty() || index != tiles.length) {
      while (deque.isEmpty()
          || (index != tiles.length && tiles[index][0] - deque.peekFirst()[0] + 1 <= carpetLen)) {
        deque.offerLast(tiles[index]);
        sum += tiles[index][1] - tiles[index][0] + 1;
        ++index;
      }

      result =
          Math.max(
              result,
              sum - Math.max(0, deque.peekLast()[1] - deque.peekFirst()[0] + 1 - carpetLen));

      sum -= deque.peekFirst()[1] - deque.peekFirst()[0] + 1;
      deque.pollFirst();
    }

    return result;
  }
}