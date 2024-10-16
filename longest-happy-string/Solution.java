import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String longestDiverseString(int a, int b, int c) {
    int[] counts = {a, b, c};
    List<Element> elements = new ArrayList<>();
    while (true) {
      Optional<Integer> index =
          IntStream.range(0, counts.length)
              .filter(i -> counts[i] != 0 && (elements.isEmpty() || i != elements.getLast().index))
              .boxed()
              .max(Comparator.comparing(i -> counts[i]));
      if (index.isEmpty()) {
        break;
      }

      --counts[index.get()];
      elements.add(new Element(index.get(), 1));
    }
    for (Element element : elements) {
      if (counts[element.index] != 0) {
        --counts[element.index];
        ++element.length;
      }
    }

    return elements.stream()
        .map(element -> String.valueOf((char) ('a' + element.index)).repeat(element.length))
        .collect(Collectors.joining());
  }
}

class Element {
  int index;
  int length;

  Element(int index, int length) {
    this.index = index;
    this.length = length;
  }
}