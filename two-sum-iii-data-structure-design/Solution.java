import java.util.HashMap;
import java.util.Map;

class TwoSum {
  Map<Integer, Integer> numberToCount = new HashMap<>();

  /** Add the number to an internal data structure.. */
  public void add(int number) {
    numberToCount.put(number, numberToCount.getOrDefault(number, 0) + 1);
  }

  /** Find if there exists any pair of numbers which sum is equal to the value. */
  public boolean find(int value) {
    return numberToCount.keySet().stream()
        .anyMatch(
            number -> {
              int other = value - number;

              return (other == number && numberToCount.get(other) >= 2)
                  || (other != number && numberToCount.containsKey(other));
            });
  }
}

// Your TwoSum object will be instantiated and called as such:
// TwoSum obj = new TwoSum();
// obj.add(number);
// boolean param_2 = obj.find(value);
