import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
  public long countValidSubstrings(String s) {
    Queue<Integer> aIndices = new ArrayDeque<>();
    Queue<Integer> bIndices = new ArrayDeque<>();
    Queue<Integer> cIndices = new ArrayDeque<>();
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == 'a') {
        aIndices.offer(i);
      } else if (s.charAt(i) == 'b') {
        bIndices.offer(i);
      } else {
        cIndices.offer(i);
      }
    }

    long result = s.length() * (s.length() + 1L) / 2;
    for (int beginIndex = 0; beginIndex < s.length(); ++beginIndex) {
      while (!aIndices.isEmpty() && aIndices.peek() < beginIndex) {
        aIndices.poll();
      }
      while (!bIndices.isEmpty() && (aIndices.isEmpty() || bIndices.peek() < aIndices.peek())) {
        bIndices.poll();
      }
      while (!cIndices.isEmpty() && (bIndices.isEmpty() || cIndices.peek() < bIndices.peek())) {
        cIndices.poll();
      }

      if (!cIndices.isEmpty()) {
        result -= s.length() - cIndices.peek();
      }
    }

    return result;
  }
}