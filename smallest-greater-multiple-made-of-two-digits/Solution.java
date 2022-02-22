import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
  public int findInteger(int k, int digit1, int digit2) {
    int[] digits = IntStream.of(digit1, digit2).sorted().toArray();

    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(0);
    while (!queue.isEmpty()) {
      int head = queue.poll();
      if (head > k && head % k == 0) {
        return head;
      }

      for (int digit : digits) {
        long next = head * 10L + digit;
        if (next != 0 && next <= Integer.MAX_VALUE) {
          queue.offer((int) next);
        }
      }
    }

    return -1;
  }
}