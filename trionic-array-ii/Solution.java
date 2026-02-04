import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public long maxSumTrionic(int[] nums) {
    return buildRanges(nums).stream()
        .filter(
            range ->
                range.beginIndex >= 1
                    && nums[range.beginIndex] > nums[range.beginIndex - 1]
                    && range.endIndex <= nums.length - 2
                    && nums[range.endIndex] < nums[range.endIndex + 1])
        .mapToLong(
            range ->
                computeMaxSuffixSum(nums, range.beginIndex - 1)
                    + computeMaxPrefixSum(nums, range.endIndex + 1)
                    + +IntStream.rangeClosed(range.beginIndex, range.endIndex)
                        .map(j -> nums[j])
                        .asLongStream()
                        .sum())
        .max()
        .getAsLong();
  }

  long computeMaxSuffixSum(int[] nums, int fromIndex) {
    long result = nums[fromIndex];
    long suffixSum = nums[fromIndex];
    for (int i = fromIndex - 1; i >= 0 && nums[i] < nums[i + 1]; --i) {
      suffixSum += nums[i];
      result = Math.max(result, suffixSum);
    }

    return result;
  }

  long computeMaxPrefixSum(int[] nums, int fromIndex) {
    long result = nums[fromIndex];
    long prefixSum = nums[fromIndex];
    for (int i = fromIndex + 1; i < nums.length && nums[i] > nums[i - 1]; ++i) {
      prefixSum += nums[i];
      result = Math.max(result, prefixSum);
    }

    return result;
  }

  List<Range> buildRanges(int[] nums) {
    List<Range> ranges = new ArrayList<>();
    for (int i = 0; i < nums.length; ++i) {
      if (!ranges.isEmpty() && nums[i] < nums[ranges.getLast().endIndex]) {
        ranges.getLast().endIndex = i;
      } else {
        ranges.add(new Range(i, i));
      }
    }

    return ranges.stream().filter(range -> range.beginIndex != range.endIndex).toList();
  }
}

class Range {
  int beginIndex;
  int endIndex;

  Range(int beginIndex, int endIndex) {
    this.beginIndex = beginIndex;
    this.endIndex = endIndex;
  }
}
