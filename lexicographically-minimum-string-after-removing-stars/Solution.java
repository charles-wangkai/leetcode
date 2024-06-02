import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String clearStars(String s) {
    @SuppressWarnings("unchecked")
    Deque<Integer>[] stacks = new Deque[26];
    for (int i = 0; i < stacks.length; ++i) {
      stacks[i] = new ArrayDeque<>();
    }

    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == '*') {
        for (Deque<Integer> stack : stacks) {
          if (!stack.isEmpty()) {
            stack.pop();

            break;
          }
        }
      } else {
        stacks[s.charAt(i) - 'a'].push(i);
      }
    }

    boolean[] used = new boolean[s.length()];
    for (Deque<Integer> stack : stacks) {
      for (int index : stack) {
        used[index] = true;
      }
    }

    return IntStream.range(0, s.length())
        .filter(i -> used[i])
        .mapToObj(s::charAt)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}