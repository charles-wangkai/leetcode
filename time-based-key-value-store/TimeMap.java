import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TimeMap {
  private Map<String, List<Element>> keyToElements = new HashMap<>();

  public void set(String key, String value, int timestamp) {
    keyToElements.putIfAbsent(key, new ArrayList<>());
    keyToElements.get(key).add(new Element(timestamp, value));
  }

  public String get(String key, int timestamp) {
    List<Element> elements = keyToElements.getOrDefault(key, List.of());
    int index =
        Collections.binarySearch(
            elements, new Element(timestamp, null), Comparator.comparing(Element::timestamp));
    if (index < 0) {
      index = -1 - index - 1;
    }

    return (index == -1) ? "" : elements.get(index).value();
  }
}

record Element(int timestamp, String value) {}

// Your TimeMap object will be instantiated and called as such:
// TimeMap obj = new TimeMap();
// obj.set(key,value,timestamp);
// String param_2 = obj.get(key,timestamp);
