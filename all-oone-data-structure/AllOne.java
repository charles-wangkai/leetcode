import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class AllOne {
  Map<String, Integer> keyToCount = new HashMap<>();
  SortedMap<Integer, Set<String>> countToKeys = new TreeMap<>();

  public void inc(String key) {
    int count = keyToCount.getOrDefault(key, 0);
    if (count != 0) {
      removeFromMap(countToKeys, count, key);
    }
    addToMap(countToKeys, count + 1, key);
    keyToCount.put(key, count + 1);
  }

  public void dec(String key) {
    int count = keyToCount.get(key);
    removeFromMap(countToKeys, count, key);
    if (count != 1) {
      addToMap(countToKeys, count - 1, key);
    }
    keyToCount.put(key, count - 1);
  }

  public String getMaxKey() {
    return countToKeys.isEmpty() ? "" : countToKeys.lastEntry().getValue().iterator().next();
  }

  public String getMinKey() {
    return countToKeys.isEmpty() ? "" : countToKeys.firstEntry().getValue().iterator().next();
  }

  void addToMap(SortedMap<Integer, Set<String>> countToKeys, int count, String key) {
    countToKeys.putIfAbsent(count, new HashSet<>());
    countToKeys.get(count).add(key);
  }

  void removeFromMap(SortedMap<Integer, Set<String>> countToKeys, int count, String key) {
    countToKeys.get(count).remove(key);
    if (countToKeys.get(count).isEmpty()) {
      countToKeys.remove(count);
    }
  }
}

// Your AllOne object will be instantiated and called as such:
// AllOne obj = new AllOne();
// obj.inc(key);
// obj.dec(key);
// String param_3 = obj.getMaxKey();
// String param_4 = obj.getMinKey();
