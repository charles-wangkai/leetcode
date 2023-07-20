import java.util.LinkedList;
import java.util.Queue;

public class Solution {
  public int integerReplacement(int n) {
    Queue<Element> queue = new LinkedList<>();
    queue.offer(new Element(n, 0));

    while (true) {
      Element head = queue.poll();
      if (head.n == 1) {
        return head.step;
      }

      if (head.n % 2 == 0) {
        queue.offer(new Element(head.n / 2, head.step + 1));
      } else {
        queue.offer(new Element(head.n + 1, head.step + 1));
        queue.offer(new Element(head.n - 1, head.step + 1));
      }
    }
  }
}

class Element {
  long n;
  int step;

  Element(long n, int step) {
    this.n = n;
    this.step = step;
  }
}
