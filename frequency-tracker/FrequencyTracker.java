import java.util.HashMap;
import java.util.Map;

class FrequencyTracker {
  Map<Integer, Integer> numberToFrequency = new HashMap<>();
  Map<Integer, Integer> frequencyToCount = new HashMap<>();

  public void add(int number) {
    updateMap(numberToFrequency, number, 1);

    int frequency = numberToFrequency.get(number);
    if (frequency != 1) {
      updateMap(frequencyToCount, frequency - 1, -1);
    }
    updateMap(frequencyToCount, frequency, 1);
  }

  public void deleteOne(int number) {
    if (numberToFrequency.containsKey(number)) {
      updateMap(numberToFrequency, number, -1);

      int frequency = numberToFrequency.getOrDefault(number, 0);
      updateMap(frequencyToCount, frequency + 1, -1);
      if (frequency != 0) {
        updateMap(frequencyToCount, frequency, 1);
      }
    }
  }

  public boolean hasFrequency(int frequency) {
    return frequencyToCount.containsKey(frequency);
  }

  void updateMap(Map<Integer, Integer> map, int key, int delta) {
    map.put(key, map.getOrDefault(key, 0) + delta);
    map.remove(key, 0);
  }
}

// Your FrequencyTracker object will be instantiated and called as such:
// FrequencyTracker obj = new FrequencyTracker();
// obj.add(number);
// obj.deleteOne(number);
// boolean param_3 = obj.hasFrequency(frequency);
