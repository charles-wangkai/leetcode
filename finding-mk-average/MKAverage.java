import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;

class MKAverage {
  Queue<Integer> stream = new ArrayDeque<>();
  SortedMap<Integer, Integer> minValueToCount = new TreeMap<>();
  SortedMap<Integer, Integer> minAlternativeValueToCount = new TreeMap<>();
  SortedMap<Integer, Integer> maxValueToCount = new TreeMap<>();
  SortedMap<Integer, Integer> maxAlternativeValueToCount = new TreeMap<>();
  int m;
  int k;
  long total = 0;
  int minSize = 0;
  int maxSize = 0;
  long minSum = 0;
  long maxSum = 0;

  public MKAverage(int m, int k) {
    this.m = m;
    this.k = k;
  }

  public void addElement(int num) {
    if (stream.size() == m) {
      int removed = stream.poll();
      total -= removed;

      if (minAlternativeValueToCount.containsKey(removed)) {
        updateMap(minAlternativeValueToCount, removed, -1);
      } else {
        updateMap(minValueToCount, removed, -1);
        --minSize;
        minSum -= removed;

        if (!minAlternativeValueToCount.isEmpty()) {
          int alternativeValue = minAlternativeValueToCount.firstKey();
          updateMap(minAlternativeValueToCount, alternativeValue, -1);
          updateMap(minValueToCount, alternativeValue, 1);
          ++minSize;
          minSum += alternativeValue;
        }
      }

      if (maxAlternativeValueToCount.containsKey(removed)) {
        updateMap(maxAlternativeValueToCount, removed, -1);
      } else {
        updateMap(maxValueToCount, removed, -1);
        --maxSize;
        maxSum -= removed;

        if (!maxAlternativeValueToCount.isEmpty()) {
          int alternativeValue = maxAlternativeValueToCount.lastKey();
          updateMap(maxAlternativeValueToCount, alternativeValue, -1);
          updateMap(maxValueToCount, alternativeValue, 1);
          ++maxSize;
          maxSum += alternativeValue;
        }
      }
    }

    stream.offer(num);
    total += num;

    if (minSize != k || num < minValueToCount.lastKey()) {
      updateMap(minValueToCount, num, 1);
      ++minSize;
      minSum += num;

      if (minSize == k + 1) {
        int alternativeValue = minValueToCount.lastKey();

        updateMap(minValueToCount, alternativeValue, -1);
        --minSize;
        minSum -= alternativeValue;

        updateMap(minAlternativeValueToCount, alternativeValue, 1);
      }
    } else {
      updateMap(minAlternativeValueToCount, num, 1);
    }

    if (maxSize != k || num > maxValueToCount.firstKey()) {
      updateMap(maxValueToCount, num, 1);
      ++maxSize;
      maxSum += num;

      if (maxSize == k + 1) {
        int alternativeValue = maxValueToCount.firstKey();

        updateMap(maxValueToCount, alternativeValue, -1);
        --maxSize;
        maxSum -= alternativeValue;

        updateMap(maxAlternativeValueToCount, alternativeValue, 1);
      }
    } else {
      updateMap(maxAlternativeValueToCount, num, 1);
    }
  }

  public int calculateMKAverage() {
    return (stream.size() < m) ? -1 : (int) ((total - minSum - maxSum) / (m - k * 2));
  }

  void updateMap(Map<Integer, Integer> map, int key, int deltaValue) {
    map.put(key, map.getOrDefault(key, 0) + deltaValue);
    map.remove(key, 0);
  }
}

// Your MKAverage object will be instantiated and called as such:
// MKAverage obj = new MKAverage(m, k);
// obj.addElement(num);
// int param_2 = obj.calculateMKAverage();
