import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int[] countSmallerOppositeParity(int[] nums) {
    Map<Integer, Integer> valueToCompressed = buildValueToCompressed(nums);

    FenwickTree[] fenwickTrees = new FenwickTree[2];
    for (int i = 0; i < fenwickTrees.length; ++i) {
      fenwickTrees[i] = new FenwickTree(valueToCompressed.size());
    }

    int[] result = new int[nums.length];
    for (int i = result.length - 1; i >= 0; --i) {
      result[i] = fenwickTrees[1 - nums[i] % 2].computePrefixSum(valueToCompressed.get(nums[i]));
      fenwickTrees[nums[i] % 2].add(valueToCompressed.get(nums[i]), 1);
    }

    return result;
  }

  Map<Integer, Integer> buildValueToCompressed(int[] nums) {
    int[] sorted = Arrays.stream(nums).sorted().distinct().toArray();

    return IntStream.range(0, sorted.length)
        .boxed()
        .collect(Collectors.toMap(i -> sorted[i], i -> i + 1));
  }
}

class FenwickTree {
  int[] a;

  FenwickTree(int size) {
    a = new int[size + 1];
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
