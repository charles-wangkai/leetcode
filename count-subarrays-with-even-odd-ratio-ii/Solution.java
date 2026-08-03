import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public long countRatioSubarrays(int[] nums, int a, int b) {
    long[] prefixSums = new long[nums.length + 1];
    for (int i = 1; i < prefixSums.length; ++i) {
      prefixSums[i] = prefixSums[i - 1] + ((nums[i - 1] % 2 == 0) ? -b : a);
    }

    Map<Long, Integer> valueToCompressed = compress(prefixSums);

    long result = 0;
    FenwickTree fenwickTree = new FenwickTree(valueToCompressed.size());
    fenwickTree.add(valueToCompressed.get(0L), 1);
    long prefixSum = 0;
    for (int num : nums) {
      prefixSum += (num % 2 == 0) ? -b : a;

      result += fenwickTree.computePrefixSum(valueToCompressed.get(prefixSum));

      fenwickTree.add(valueToCompressed.get(prefixSum), 1);
    }

    return result;
  }

  Map<Long, Integer> compress(long[] values) {
    long[] sorted = Arrays.stream(values).sorted().distinct().toArray();

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
