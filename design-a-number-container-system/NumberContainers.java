import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

class NumberContainers {
  Map<Integer, Integer> indexToValue = new HashMap<>();
  Map<Integer, SortedSet<Integer>> valueToIndices = new HashMap<>();

  public void change(int index, int number) {
    if (indexToValue.containsKey(index)) {
      valueToIndices.get(indexToValue.get(index)).remove(index);
    }

    indexToValue.put(index, number);
    valueToIndices.putIfAbsent(number, new TreeSet<>());
    valueToIndices.get(number).add(index);
  }

  public int find(int number) {
    return valueToIndices.getOrDefault(number, Collections.emptySortedSet()).isEmpty()
        ? -1
        : valueToIndices.get(number).first();
  }
}

// Your NumberContainers object will be instantiated and called as such:
// NumberContainers obj = new NumberContainers();
// obj.change(index,number);
// int param_2 = obj.find(number);
