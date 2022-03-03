import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Solution {
  static Map<State, Integer> cache = new HashMap<>();

  public int houseOfCards(int n) {
    int result = 0;
    for (int i = 1; ; ++i) {
      int restSum = n - i * 2;
      if (restSum < i * (i - 1) / 2 * 3) {
        break;
      }

      if (restSum % 3 == 0) {
        result += search(i, restSum / 3, 0);
      }
    }

    return result;
  }

  int search(int restRow, int restNum, int lower) {
    if (restRow == 1) {
      return 1;
    }

    State state = new State(restRow, restNum, lower);
    if (!cache.containsKey(state)) {
      int result = 0;
      for (int i = lower;
          restNum - i >= (restRow - 1) * (i + 1) + (restRow - 1) * (restRow - 2) / 2;
          ++i) {
        result += search(restRow - 1, restNum - i, i + 1);
      }

      cache.put(state, result);
    }

    return cache.get(state);
  }
}

class State {
  int restRow;
  int restNum;
  int lower;

  State(int restRow, int restNum, int lower) {
    this.restRow = restRow;
    this.restNum = restNum;
    this.lower = lower;
  }

  @Override
  public int hashCode() {
    return Objects.hash(restRow, restRow, lower);
  }

  @Override
  public boolean equals(Object obj) {
    State other = (State) obj;

    return restRow == other.restRow && restNum == other.restNum && lower == other.lower;
  }
}