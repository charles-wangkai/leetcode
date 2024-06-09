import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public int findWinningPlayer(int[] skills, int k) {
    Deque<Integer> deque = new ArrayDeque<>();
    for (int i = 0; i < skills.length; ++i) {
      deque.offerLast(i);
    }

    int winCount = 0;
    while (true) {
      int index1 = deque.pollFirst();
      int index2 = deque.pollFirst();
      if (skills[index1] > skills[index2]) {
        ++winCount;

        deque.offerFirst(index1);
        deque.offerLast(index2);
      } else {
        winCount = 1;

        deque.offerFirst(index2);
        deque.offerLast(index1);
      }

      if (winCount == Math.min(k, skills.length - 1)) {
        return deque.peekFirst();
      }
    }
  }
}