import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

class Solution {
  public long[] findXSum(int[] nums, int k, int x) {
    State state = new State(x);
    for (int i = 0; i < k - 1; ++i) {
      state.update(nums[i], 1);
    }

    long[] result = new long[nums.length - k + 1];
    for (int i = 0; i < result.length; ++i) {
      state.update(nums[i + k - 1], 1);
      result[i] = state.chosenSum;
      state.update(nums[i], -1);
    }

    return result;
  }
}

class State {
  int x;
  Map<Integer, Integer> valueToCount = new HashMap<>();
  Comparator<Integer> countComparator =
      Comparator.<Integer, Integer>comparing(valueToCount::get).thenComparing(value -> value);
  SortedSet<Integer> chosen = new TreeSet<>(countComparator);
  long chosenSum;
  SortedSet<Integer> backups = new TreeSet<>(countComparator);

  State(int x) {
    this.x = x;
  }

  void update(int value, int countDelta) {
    int oldCount = valueToCount.getOrDefault(value, 0);

    if (valueToCount.containsKey(value)) {
      if (chosen.contains(value)) {
        chosen.remove(value);
        chosenSum -= (long) value * oldCount;
      } else {
        backups.remove(value);
      }
    }

    int newCount = oldCount + countDelta;
    valueToCount.put(value, newCount);
    if (newCount != 0) {
      if (!chosen.isEmpty()
          && (newCount > valueToCount.get(chosen.first())
              || (newCount == valueToCount.get(chosen.first()) && value > chosen.first()))) {
        chosen.add(value);
        chosenSum += (long) value * newCount;
      } else {
        backups.add(value);
      }
    }

    if (chosen.size() == x + 1) {
      int v = chosen.removeFirst();
      chosenSum -= (long) v * valueToCount.get(v);

      backups.add(v);
    } else if (chosen.size() != x && !backups.isEmpty()) {
      int v = backups.removeLast();

      chosen.add(v);
      chosenSum += (long) v * valueToCount.get(v);
    }
  }
}