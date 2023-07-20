import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public String removeDuplicates(String s, int k) {
    Deque<Element> stack = new ArrayDeque<>();
    for (char letter : s.toCharArray()) {
      if (!stack.isEmpty() && stack.peek().letter == letter) {
        ++stack.peek().count;

        if (stack.peek().count == k) {
          stack.pop();
        }
      } else {
        stack.push(new Element(letter, 1));
      }
    }

    List<Element> reversed = new ArrayList<>(stack);
    Collections.reverse(reversed);

    return reversed.stream()
        .map(e -> String.valueOf(e.letter).repeat(e.count))
        .collect(Collectors.joining());
  }
}

class Element {
  char letter;
  int count;

  Element(char letter, int count) {
    this.letter = letter;
    this.count = count;
  }
}
