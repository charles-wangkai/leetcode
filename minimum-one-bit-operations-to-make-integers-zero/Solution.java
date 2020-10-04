import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Solution {
  Map<State, Element> cache = new HashMap<>();

  public int minimumOneBitOperations(int n) {
    int[] digits = Integer.toBinaryString(n).chars().map(ch -> ch - '0').toArray();

    int result = 0;
    for (int i = 0; i < digits.length; ++i) {
      result += transform(digits, i, 0);
    }

    return result;
  }

  int transform(int[] digits, int index, int target) {
    if (digits[index] == target) {
      return 0;
    }

    State state = new State(buildSuffix(digits, index), target);
    if (!cache.containsKey(state)) {
      int step = 1;
      if (index != digits.length - 1) {
        step += transform(digits, index + 1, 1);

        for (int i = index + 2; i < digits.length; ++i) {
          step += transform(digits, i, 0);
        }
      }
      digits[index] = target;

      cache.put(state, new Element(buildSuffix(digits, index), step));
    }

    Element element = cache.get(state);
    for (int i = index; i < digits.length; ++i) {
      digits[i] = element.suffix.charAt(i - index) - '0';
    }

    return element.step;
  }

  String buildSuffix(int[] digits, int index) {
    StringBuilder result = new StringBuilder();
    for (int i = index; i < digits.length; ++i) {
      result.append(digits[i]);
    }

    return result.toString();
  }
}

class State {
  String suffix;
  int target;

  State(String suffix, int target) {
    this.suffix = suffix;
    this.target = target;
  }

  @Override
  public int hashCode() {
    return Objects.hash(suffix, target);
  }

  @Override
  public boolean equals(Object obj) {
    State other = (State) obj;

    return Objects.equals(suffix, other.suffix) && target == other.target;
  }
}

class Element {
  String suffix;
  int step;

  Element(String suffix, int step) {
    this.suffix = suffix;
    this.step = step;
  }
}
