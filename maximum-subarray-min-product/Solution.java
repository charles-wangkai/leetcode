import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

class Solution {
  public int maxSumMinProduct(int[] nums) {
    long[] prefixSums = new long[nums.length + 1];
    for (int i = 1; i < prefixSums.length; ++i) {
      prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
    }

    NavigableSet<Integer> usedIndices = new TreeSet<>();
    usedIndices.add(-1);
    usedIndices.add(nums.length);

    long maxMinProduct = 0;
    int[] sortedIndices =
        IntStream.range(0, nums.length)
            .boxed()
            .sorted(Comparator.comparing(i -> nums[i]))
            .mapToInt(x -> x)
            .toArray();
    for (int index : sortedIndices) {
      int lowerIndex = usedIndices.lower(index);
      int upperIndex = usedIndices.higher(index);

      maxMinProduct =
          Math.max(
              maxMinProduct, nums[index] * (prefixSums[upperIndex] - prefixSums[lowerIndex + 1]));
      usedIndices.add(index);
    }

    return (int) (maxMinProduct % 1_000_000_007);
  }
}
