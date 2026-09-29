import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int[] rearrangeArray(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    return valueToCount.keySet().stream()
        .flatMap(
            value ->
                IntStream.range(0, valueToCount.get(value)).boxed().map(i -> new Element(i, value)))
        .sorted(Comparator.comparing(Element::sequence).thenComparing(Element::value))
        .mapToInt(Element::value)
        .toArray();
  }
}

record Element(int sequence, int value) {}
