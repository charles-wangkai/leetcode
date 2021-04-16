import java.util.Stack;
import java.util.stream.Collectors;

class Solution {
  public String removeDuplicates(String s, int k) {
    Stack<Element> stack = new Stack<>();
    for (char letter : s.toCharArray()) {
      if (!stack.empty() && stack.peek().letter == letter) {
        ++stack.peek().count;

        if (stack.peek().count == k) {
          stack.pop();
        }
      } else {
        stack.push(new Element(letter, 1));
      }
    }

    return stack.stream()
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
