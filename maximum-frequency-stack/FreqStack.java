import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

class FreqStack {
  private Map<Integer, Element> valueToElement = new HashMap<>();
  private SortedSet<Element> elements =
      new TreeSet<>(
          Comparator.comparing((Element e) -> e.sequences.size())
              .thenComparing(e -> e.sequences.peek()));
  private int sequence = 0;

  public void push(int val) {
    Element element;
    if (valueToElement.containsKey(val)) {
      element = valueToElement.get(val);
      elements.remove(element);
    } else {
      element = new Element(val);
      valueToElement.put(val, element);
    }

    element.sequences.push(sequence);
    elements.add(element);
    ++sequence;
  }

  public int pop() {
    Element element = elements.last();
    elements.remove(element);

    element.sequences.pop();
    if (element.sequences.isEmpty()) {
      valueToElement.remove(element.value);
    } else {
      elements.add(element);
    }

    return element.value;
  }
}

class Element {
  int value;
  Deque<Integer> sequences = new ArrayDeque<>();

  Element(int value) {
    this.value = value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, sequences);
  }

  @Override
  public boolean equals(Object obj) {
    Element other = (Element) obj;

    return value == other.value && sequences.equals(other.sequences);
  }
}

// Your FreqStack object will be instantiated and called as such:
// FreqStack obj = new FreqStack();
// obj.push(val);
// int param_2 = obj.pop();
