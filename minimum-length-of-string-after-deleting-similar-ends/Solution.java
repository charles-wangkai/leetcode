import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public int minimumLength(String s) {
    Deque<Element> deque = new ArrayDeque<>();
    int count = -1;
    char prev = 0;
    for (int i = 0; i <= s.length(); ++i) {
      if (i != s.length() && s.charAt(i) == prev) {
        ++count;
      } else {
        if (count != -1) {
          deque.offerLast(new Element(prev, count));
        }

        count = 1;
        if (i != s.length()) {
          prev = s.charAt(i);
        }
      }
    }

    while (true) {
      if (deque.size() == 1) {
        if (deque.peekFirst().count >= 2) {
          deque.pollFirst();
        }

        break;
      }

      if (deque.peekFirst().ch != deque.peekLast().ch) {
        break;
      }

      deque.pollFirst();
      deque.pollLast();
    }

    return deque.stream().mapToInt(e -> e.count).sum();
  }
}

class Element {
  char ch;
  int count;

  Element(char ch, int count) {
    this.ch = ch;
    this.count = count;
  }
}
