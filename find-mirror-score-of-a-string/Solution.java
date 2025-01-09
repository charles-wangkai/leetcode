import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public long calculateScore(String s) {
    @SuppressWarnings("unchecked")
    Deque<Integer>[] stacks = new Deque[26];
    for (int i = 0; i < stacks.length; ++i) {
      stacks[i] = new ArrayDeque<>();
    }

    long result = 0;
    for (int i = 0; i < s.length(); ++i) {
      int mirror = 'z' - s.charAt(i);
      if (!stacks[mirror].isEmpty()) {
        result += i - stacks[mirror].pop();
      } else {
        stacks[s.charAt(i) - 'a'].push(i);
      }
    }

    return result;
  }
}