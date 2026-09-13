import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public long distantSubarrays(int[] nums, int goal, int k) {
    if (k == 0) {
      return nums.length * (nums.length + 1L) / 2;
    }

    List<Long> values = new ArrayList<>();
    values.add(0L);
    long prefixSum = 0;
    for (int num : nums) {
      prefixSum += num;

      values.add(prefixSum - goal - k);
      values.add(prefixSum - goal + k - 1);
      values.add(prefixSum);
    }

    Map<Long, Integer> valueToCompressed = buildValueToCompressed(values);

    FenwickTree fenwickTree = new FenwickTree(valueToCompressed.size());
    fenwickTree.add(valueToCompressed.get(0L), 1);

    long result = 0;
    prefixSum = 0;
    for (int i = 0; i < nums.length; ++i) {
      prefixSum += nums[i];

      result +=
          fenwickTree.computePrefixSum(valueToCompressed.get(prefixSum - goal - k))
              + (i
                  + 1
                  - fenwickTree.computePrefixSum(valueToCompressed.get(prefixSum - goal + k - 1)));

      fenwickTree.add(valueToCompressed.get(prefixSum), 1);
    }

    return result;
  }

  Map<Long, Integer> buildValueToCompressed(List<Long> values) {
    long[] sorted = values.stream().mapToLong(Long::longValue).sorted().distinct().toArray();

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
