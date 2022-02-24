import java.util.Arrays;
import java.util.Comparator;
import java.util.OptionalInt;
import java.util.stream.IntStream;

class Solution {
  public long largestEvenSum(int[] nums, int k) {
    int[] sorted =
        Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).mapToInt(x -> x).toArray();

    long sum = IntStream.range(0, k).map(i -> sorted[i]).asLongStream().sum();
    if (sum % 2 == 0) {
      return sum;
    }

    OptionalInt largeMinEven =
        IntStream.range(0, k).map(i -> sorted[i]).filter(x -> x % 2 == 0).min();
    OptionalInt largeMinOdd =
        IntStream.range(0, k).map(i -> sorted[i]).filter(x -> x % 2 != 0).min();
    OptionalInt smallMaxEven =
        IntStream.range(k, sorted.length).map(i -> sorted[i]).filter(x -> x % 2 == 0).max();
    OptionalInt smallMaxOdd =
        IntStream.range(k, sorted.length).map(i -> sorted[i]).filter(x -> x % 2 != 0).max();

    int minDiff = Integer.MAX_VALUE;
    if (largeMinEven.isPresent() && smallMaxOdd.isPresent()) {
      minDiff = Math.min(minDiff, largeMinEven.getAsInt() - smallMaxOdd.getAsInt());
    }
    if (largeMinOdd.isPresent() && smallMaxEven.isPresent()) {
      minDiff = Math.min(minDiff, largeMinOdd.getAsInt() - smallMaxEven.getAsInt());
    }

    return (minDiff == Integer.MAX_VALUE) ? -1 : (sum - minDiff);
  }
} 