import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public long minInversionCount(int[] nums, int k) {
    int[] sortedValues = Arrays.stream(nums).distinct().sorted().toArray();
    Map<Integer, Integer> valueToCompressed =
        IntStream.range(0, sortedValues.length)
            .boxed()
            .collect(Collectors.toMap(i -> sortedValues[i], i -> i + 1));

    FenwickTree fenwickTree = new FenwickTree(sortedValues.length);
    long inversionNum = 0;
    for (int i = 0; i < k - 1; ++i) {
      inversionNum += i - fenwickTree.computePrefixSum(valueToCompressed.get(nums[i]));
      fenwickTree.add(valueToCompressed.get(nums[i]), 1);
    }

    long result = Long.MAX_VALUE;
    for (int i = k - 1; i < nums.length; ++i) {
      inversionNum += (k - 1) - fenwickTree.computePrefixSum(valueToCompressed.get(nums[i]));
      fenwickTree.add(valueToCompressed.get(nums[i]), 1);

      result = Math.min(result, inversionNum);

      fenwickTree.add(valueToCompressed.get(nums[i - k + 1]), -1);
      inversionNum -= fenwickTree.computePrefixSum(valueToCompressed.get(nums[i - k + 1]) - 1);
    }

    return result;
  }
}

class FenwickTree {
  int[] a;

  FenwickTree(int size) {
    a = new int[Integer.highestOneBit(size) * 2 + 1];
  }

  void add(int pos, int delta) {
    while (pos < a.length) {
      a[pos] += delta;
      pos += pos & -pos;
    }
  }

  int computePrefixSum(int pos) {
    int result = 0;
    while (pos != 0) {
      result += a[pos];
      pos -= pos & -pos;
    }

    return result;
  }
}
