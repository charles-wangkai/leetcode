import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public int minimumLength(String s) {
    Deque<Character> deque = new ArrayDeque<>();
    for (char c : s.toCharArray()) {
      deque.offerLast(c);
    }

    while (deque.size() >= 2 && deque.peekFirst().equals(deque.peekLast())) {
      char c = deque.peekFirst();

      while (!deque.isEmpty() && deque.peekFirst() == c) {
        deque.pollFirst();
      }
      while (!deque.isEmpty() && deque.peekLast() == c) {
        deque.pollLast();
      }
    }

    return deque.size();
  }
}
