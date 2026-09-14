// https://leetcode.com/problems/count-shadow-pairs-ii/solutions/8518618/python3-divide-and-conquer-coordinate-co-dc90/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int shadowPairs(int[] nums) {
    int[] sorted = Arrays.stream(nums).sorted().distinct().toArray();
    Map<Integer, Integer> valueToCompressed =
        IntStream.range(0, sorted.length).boxed().collect(Collectors.toMap(i -> sorted[i], i -> i));

    return search(
        Arrays.stream(nums).map(valueToCompressed::get).toArray(), 0, valueToCompressed.size());
  }

  int search(int[] values, int lowerValue, int upperValue) {
    if (upperValue - lowerValue <= 1 || values.length < 2) {
      return 0;
    }

    int middleValue = (lowerValue + upperValue) / 2;

    int result = 0;
    List<Integer> lowerStack = new ArrayList<>();
    List<Integer> upperStack = new ArrayList<>();
    for (int i = 0; i < values.length; ++i) {
      if (values[i] < middleValue) {
        while (!lowerStack.isEmpty() && values[lowerStack.getLast()] < values[i]) {
          lowerStack.removeLast();
        }

        lowerStack.add(i);
      } else {
        while (!upperStack.isEmpty() && values[upperStack.getLast()] >= values[i]) {
          upperStack.removeLast();
        }

        int p = upperStack.isEmpty() ? -1 : upperStack.getLast();

        result += lowerStack.size() - (-1 - Collections.binarySearch(lowerStack, p));

        upperStack.add(i);
      }
    }

    result +=
        search(
                Arrays.stream(values).filter(x -> x < middleValue).toArray(),
                lowerValue,
                middleValue)
            + search(
                Arrays.stream(values).filter(x -> x >= middleValue).toArray(),
                middleValue,
                upperValue);

    return result;
  }
}
