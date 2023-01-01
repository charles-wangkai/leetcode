import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public int minimumOperations(int[] nums) {
    Deque<Long> deque = new ArrayDeque<>();
    for (int num : nums) {
      deque.offerLast((long) num);
    }

    int result = 0;
    while (!deque.isEmpty()) {
      if (deque.peekFirst() < deque.peekLast()) {
        long first1 = deque.pollFirst();
        long first2 = deque.pollFirst();
        deque.offerFirst(first1 + first2);
        ++result;
      } else if (deque.peekFirst() > deque.peekLast()) {
        long last1 = deque.pollLast();
        long last2 = deque.pollLast();
        deque.offerLast(last1 + last2);
        ++result;
      } else {
        deque.pollFirst();
        if (!deque.isEmpty()) {
          deque.pollLast();
        }
      }
    }

    return result;
  }
}
