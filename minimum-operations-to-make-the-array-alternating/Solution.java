import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int minimumOperations(int[] nums) {
    if (nums.length == 1) {
      return 0;
    }

    Element[] evenSorted =
        buildSorted(
            IntStream.range(0, nums.length).filter(i -> i % 2 == 0).map(i -> nums[i]).toArray());
    Element[] oddSorted =
        buildSorted(
            IntStream.range(0, nums.length).filter(i -> i % 2 != 0).map(i -> nums[i]).toArray());

    if (evenSorted[0].value != oddSorted[0].value) {
      return nums.length - evenSorted[0].count - oddSorted[0].count;
    }

    int nextEvenCount = (evenSorted.length == 1) ? 0 : evenSorted[1].count;
    int nextOddCount = (oddSorted.length == 1) ? 0 : oddSorted[1].count;

    return nums.length
        - Math.max(evenSorted[0].count + nextOddCount, oddSorted[0].count + nextEvenCount);
  }

  Element[] buildSorted(int[] values) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : values) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    return valueToCount.keySet().stream()
        .sorted(Comparator.comparing(valueToCount::get).reversed())
        .map(value -> new Element(value, valueToCount.get(value)))
        .toArray(Element[]::new);
  }
}

class Element {
  int value;
  int count;

  Element(int value, int count) {
    this.value = value;
    this.count = count;
  }
}