import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class RandomizedSet {
  private static Random random = new Random();

  private List<Integer> values = new ArrayList<>();
  private Map<Integer, Integer> valueToIndex = new HashMap<>();

  public boolean insert(int val) {
    if (valueToIndex.containsKey(val)) {
      return false;
    }

    values.add(val);
    valueToIndex.put(val, values.size() - 1);

    return true;
  }

  public boolean remove(int val) {
    if (!valueToIndex.containsKey(val)) {
      return false;
    }

    swap(val, values.get(values.size() - 1));
    values.remove(values.size() - 1);
    valueToIndex.remove(val);

    return true;
  }

  private void swap(int value1, int value2) {
    int index1 = valueToIndex.get(value1);
    int index2 = valueToIndex.get(value2);

    values.set(index1, value2);
    values.set(index2, value1);

    valueToIndex.put(value1, index2);
    valueToIndex.put(value2, index1);
  }

  public int getRandom() {
    return values.get(random.nextInt(values.size()));
  }
}

// Your RandomizedSet object will be instantiated and called as such:
// RandomizedSet obj = new RandomizedSet();
// boolean param_1 = obj.insert(val);
// boolean param_2 = obj.remove(val);
// int param_3 = obj.getRandom();
