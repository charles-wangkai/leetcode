import java.util.Arrays;

class Solution {
  public int shipWithinDays(int[] weights, int days) {
    int result = -1;
    int lower = Arrays.stream(weights).max().getAsInt();
    int upper = Arrays.stream(weights).sum();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(weights, days, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] weights, int days, int capacity) {
    int dayNum = 0;
    int rest = 0;
    for (int weight : weights) {
      if (weight > rest) {
        ++dayNum;
        rest = capacity;
      }

      rest -= weight;
    }

    return dayNum <= days;
  }
}
