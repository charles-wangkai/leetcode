import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
  public String removeKdigits(String num, int k) {
    if (k == num.length()) {
      return "0";
    }

    @SuppressWarnings("unchecked")
    Queue<Integer>[] indexQueues = new Queue[10];
    for (int i = 0; i < indexQueues.length; ++i) {
      indexQueues[i] = new ArrayDeque<>();
    }
    for (int i = 0; i < num.length(); ++i) {
      indexQueues[num.charAt(i) - '0'].offer(i);
    }

    StringBuilder result = new StringBuilder();
    int beginIndex = 0;
    for (int i = 0; i < num.length() - k; ++i) {
      for (int d = 0; ; ++d) {
        while (!indexQueues[d].isEmpty() && indexQueues[d].peek() < beginIndex) {
          indexQueues[d].poll();
        }

        if (!indexQueues[d].isEmpty()
            && num.length() - indexQueues[d].peek() >= num.length() - k - i) {
          if (d != 0 || !result.isEmpty()) {
            result.append(d);
          }

          beginIndex = indexQueues[d].poll() + 1;

          break;
        }
      }
    }

    return result.isEmpty() ? "0" : result.toString();
  }
}
