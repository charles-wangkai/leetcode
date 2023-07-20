import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
  public boolean isNStraightHand(int[] hand, int W) {
    if (hand.length % W != 0) {
      return false;
    }

    SortedMap<Integer, Integer> numberToCount = new TreeMap<>();
    for (int number : hand) {
      numberToCount.put(number, numberToCount.getOrDefault(number, 0) + 1);
    }

    while (!numberToCount.isEmpty()) {
      int minNumber = numberToCount.firstKey();
      for (int number = minNumber; number < minNumber + W; number++) {
        if (!numberToCount.containsKey(number)) {
          return false;
        }

        numberToCount.put(number, numberToCount.get(number) - 1);
        if (numberToCount.get(number) == 0) {
          numberToCount.remove(number);
        }
      }
    }

    return true;
  }
}
