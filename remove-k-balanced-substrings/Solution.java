import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public String removeSubstring(String s, int k) {
    List<Element> elements = new ArrayList<>();
    for (char c : s.toCharArray()) {
      if (!elements.isEmpty() && elements.getLast().c == c) {
        ++elements.getLast().length;
      } else {
        elements.add(new Element(c, 1));
      }

      if (elements.size() >= 2
          && elements.getLast().c == ')'
          && elements.getLast().length == k
          && elements.get(elements.size() - 2).c == '('
          && elements.get(elements.size() - 2).length >= k) {
        elements.removeLast();

        elements.getLast().length -= k;
        if (elements.getLast().length == 0) {
          elements.removeLast();
        }
      }
    }

    return elements.stream()
        .map(element -> String.valueOf(element.c).repeat(element.length))
        .collect(Collectors.joining());
  }
}

class Element {
  char c;
  int length;

  Element(char c, int length) {
    this.c = c;
    this.length = length;
  }
}