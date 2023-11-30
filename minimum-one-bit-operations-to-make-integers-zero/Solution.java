import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  Map<State, Element> cache = new HashMap<>();

  public int minimumOneBitOperations(int n) {
    int[] digits = Integer.toBinaryString(n).chars().map(c -> c - '0').toArray();

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
      digits[i] = element.suffix().charAt(i - index) - '0';
    }

    return element.step();
  }

  String buildSuffix(int[] digits, int index) {
    return IntStream.range(index, digits.length)
        .map(i -> digits[i])
        .mapToObj(String::valueOf)
        .collect(Collectors.joining());
  }
}

record State(String suffix, int target) {}

record Element(String suffix, int step) {}
