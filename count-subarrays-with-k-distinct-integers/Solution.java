import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public long countSubarrays(int[] nums, int k, int m) {
    State leftState = new State(nums, k, m);
    State rightState = new State(nums, k, m);

    long result = 0;
    for (int i = 0; i < nums.length; ++i) {
      while (leftState.canExtendEnd() && leftState.isTooShort() && !leftState.isTooLong()) {
        leftState.extendEnd();
      }

      while (rightState.canExtendEnd() && !rightState.isTooLong()) {
        rightState.extendEnd();
      }
      if (rightState.isTooLong()) {
        rightState.shrinkEnd();
      }

      if (leftState.isValid()) {
        result += rightState.endIndex - leftState.endIndex + 1;
      }

      leftState.shrinkBegin();
      rightState.shrinkBegin();
    }

    return result;
  }
}

class State {
  int[] nums;
  int k;
  int m;
  Map<Integer, Integer> valueToFreq = new HashMap<>();
  SortedMap<Integer, Integer> freqToCount = new TreeMap<>();
  int beginIndex = 0;
  int endIndex = -1;

  State(int[] nums, int k, int m) {
    this.nums = nums;
    this.k = k;
    this.m = m;
  }

  boolean isValid() {
    return !isTooShort() && !isTooLong();
  }

  boolean isTooShort() {
    return valueToFreq.size() < k || freqToCount.firstKey() < m;
  }

  boolean isTooLong() {
    return valueToFreq.size() > k;
  }

  boolean canExtendEnd() {
    return endIndex != nums.length - 1;
  }

  void extendEnd() {
    ++endIndex;
    add(nums[endIndex]);
  }

  void add(int value) {
    int oldFreq = valueToFreq.getOrDefault(value, 0);
    if (oldFreq != 0) {
      updateMap(freqToCount, oldFreq, -1);
    }

    updateMap(valueToFreq, value, 1);
    updateMap(freqToCount, oldFreq + 1, 1);
  }

  void shrinkBegin() {
    remove(nums[beginIndex]);
    ++beginIndex;
  }

  void shrinkEnd() {
    remove(nums[endIndex]);
    --endIndex;
  }

  void remove(int value) {
    int oldFreq = valueToFreq.get(value);
    updateMap(freqToCount, oldFreq, -1);

    updateMap(valueToFreq, value, -1);
    if (oldFreq != 1) {
      updateMap(freqToCount, oldFreq - 1, 1);
    }
  }

  void updateMap(Map<Integer, Integer> map, int key, int delta) {
    map.put(key, map.getOrDefault(key, 0) + delta);
    map.remove(key, 0);
  }
}
