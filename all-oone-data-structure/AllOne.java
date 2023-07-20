import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllOne {
  Map<String, Integer> key2value;
  Integer minValue;
  Integer maxValue;
  Map<Integer, Node> value2node;

  /** Initialize your data structure here. */
  public AllOne() {
    key2value = new HashMap<>();
    minValue = null;
    maxValue = null;
    value2node = new HashMap<>();
  }

  /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
  public void inc(String key) {
    int oldValue;
    if (key2value.containsKey(key)) {
      oldValue = key2value.get(key);
    } else {
      oldValue = 0;
    }

    insert(key, oldValue + 1);

    if (oldValue != 0) {
      remove(key, oldValue);
    }
  }

  /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
  public void dec(String key) {
    if (!key2value.containsKey(key)) {
      return;
    }

    int oldValue = key2value.get(key);

    if (oldValue != 1) {
      insert(key, oldValue - 1);
    }

    remove(key, oldValue);
  }

  /** Returns one of the keys with maximal value. */
  public String getMaxKey() {
    if (maxValue == null) {
      return "";
    } else {
      return value2node.get(maxValue).keys.iterator().next();
    }
  }

  /** Returns one of the keys with Minimal value. */
  public String getMinKey() {
    if (minValue == null) {
      return "";
    } else {
      return value2node.get(minValue).keys.iterator().next();
    }
  }

  private void remove(String key, int value) {
    if (key2value.get(key) == value) {
      key2value.remove(key);
    }

    Node node = value2node.get(value);
    node.keys.remove(key);
    if (node.keys.isEmpty()) {
      if (minValue == value) {
        minValue = node.next;
      }
      if (maxValue == value) {
        maxValue = node.prev;
      }

      Integer prev = node.prev;
      Integer next = node.next;
      if (prev != null) {
        value2node.get(prev).next = next;
      }
      if (next != null) {
        value2node.get(next).prev = prev;
      }

      value2node.remove(value);
    }
  }

  private void insert(String key, int value) {
    key2value.put(key, value);

    if (value2node.containsKey(value)) {
      value2node.get(value).keys.add(key);
    } else {
      Node node = new Node();

      Set<String> keys = new HashSet<>(Arrays.asList(key));
      node.keys = keys;

      Integer prev;
      if (value2node.containsKey(value - 1)) {
        prev = value - 1;
      } else if (value == 1) {
        prev = null;
      } else {
        prev = value2node.get(value + 1).prev;
      }

      Integer next;
      if (value2node.containsKey(value + 1)) {
        next = value + 1;
      } else if (value == 1) {
        next = minValue;
      } else {
        next = value2node.get(value - 1).next;
      }

      node.prev = prev;
      node.next = next;

      if (prev != null) {
        value2node.get(prev).next = value;
      }
      if (next != null) {
        value2node.get(next).prev = value;
      }

      value2node.put(value, node);
    }

    if (minValue == null || value < minValue) {
      minValue = value;
    }
    if (maxValue == null || value > maxValue) {
      maxValue = value;
    }
  }
}

class Node {
  Set<String> keys;
  Integer next;
  Integer prev;
}

// Your AllOne object will be instantiated and called as such:
// AllOne obj = new AllOne();
// obj.inc(key);
// obj.dec(key);
// String param_3 = obj.getMaxKey();
// String param_4 = obj.getMinKey();
