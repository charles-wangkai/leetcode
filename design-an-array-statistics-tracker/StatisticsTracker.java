import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

class StatisticsTracker {
  Queue<Integer> queue = new ArrayDeque<>();
  long sum = 0;

  SortedMap<Integer, Integer> lowerValueToCount = new TreeMap<>();
  int lowerSize = 0;
  SortedMap<Integer, Integer> upperValueToCount = new TreeMap<>();
  int upperSize = 0;

  Map<Integer, Integer> valueToCount = new HashMap<>();
  SortedMap<Integer, SortedSet<Integer>> countToValues = new TreeMap<>();

  public void addNumber(int number) {
    queue.offer(number);
    sum += number;

    if (upperValueToCount.isEmpty() || number >= upperValueToCount.firstKey()) {
      updateMap(upperValueToCount, number, 1);
      ++upperSize;
    } else {
      updateMap(lowerValueToCount, number, 1);
      ++lowerSize;
    }
    balance();

    updateValueCount(number, 1);
  }

  public void removeFirstAddedNumber() {
    int value = queue.poll();

    sum -= value;

    if (value >= upperValueToCount.firstKey()) {
      updateMap(upperValueToCount, value, -1);
      --upperSize;
    } else {
      updateMap(lowerValueToCount, value, -1);
      --lowerSize;
    }
    balance();

    updateValueCount(value, -1);
  }

  public int getMean() {
    return (int) (sum / queue.size());
  }

  public int getMedian() {
    return upperValueToCount.firstKey();
  }

  public int getMode() {
    return countToValues.lastEntry().getValue().first();
  }

  void updateValueCount(int value, int delta) {
    int oldCount = valueToCount.getOrDefault(value, 0);
    if (oldCount != 0) {
      valueToCount.remove(value);

      countToValues.get(oldCount).remove(value);
      if (countToValues.get(oldCount).isEmpty()) {
        countToValues.remove(oldCount);
      }
    }

    int newCount = oldCount + delta;
    if (newCount != 0) {
      valueToCount.put(value, newCount);

      countToValues.putIfAbsent(newCount, new TreeSet<>());
      countToValues.get(newCount).add(value);
    }
  }

  void balance() {
    if (upperSize == lowerSize - 1) {
      int value = lowerValueToCount.lastKey();

      updateMap(lowerValueToCount, value, -1);
      --lowerSize;

      updateMap(upperValueToCount, value, 1);
      ++upperSize;
    } else if (upperSize == lowerSize + 2) {
      int value = upperValueToCount.firstKey();

      updateMap(upperValueToCount, value, -1);
      --upperSize;

      updateMap(lowerValueToCount, value, 1);
      ++lowerSize;
    }
  }

  void updateMap(Map<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}

 // Your StatisticsTracker object will be instantiated and called as such:
 // StatisticsTracker obj = new StatisticsTracker();
 // obj.addNumber(number);
 // obj.removeFirstAddedNumber();
 // int param_3 = obj.getMean();
 // int param_4 = obj.getMedian();
 // int param_5 = obj.getMode();
