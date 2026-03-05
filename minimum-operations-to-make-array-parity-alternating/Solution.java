import java.util.List;
import java.util.stream.Stream;

class Solution {
  public int[] makeParityAlternating(int[] nums) {
    Outcome outcome = merge(computeOutcome(nums, 0), computeOutcome(nums, 1));

    return new int[] {outcome.operationNum(), outcome.spread()};
  }

  Outcome computeOutcome(int[] nums, int evenParity) {
    List<Range> ranges = List.of();
    int operationNum = 0;
    for (int i = 0; i < nums.length; ++i) {
      if (Math.floorMod(nums[i], 2) == ((i % 2 == 0) ? evenParity : (1 - evenParity))) {
        ranges = buildNextRanges(ranges, nums[i]);
      } else {
        ++operationNum;
        ranges = buildNextRanges(ranges, nums[i] - 1, nums[i] + 1);
      }
    }

    return new Outcome(
        operationNum,
        ranges.stream().mapToInt(range -> range.max() - range.min()).min().getAsInt());
  }

  List<Range> buildNextRanges(List<Range> ranges, int candidate1, int candidate2) {
    if (ranges.isEmpty()) {
      return List.of(new Range(candidate1, candidate1), new Range(candidate2, candidate2));
    }

    return ranges.stream()
        .flatMap(
            range -> {
              if ((range.min() <= candidate1 && range.max() >= candidate1)
                  || (range.min() <= candidate2 && range.max() >= candidate2)) {
                return Stream.of(range);
              }

              int middle = candidate1 + 1;
              if (range.min() == middle) {
                return Stream.of(new Range(candidate1, middle), new Range(middle, candidate2));
              }

              return (range.min() > candidate1)
                  ? Stream.of(new Range(candidate2, range.max()))
                  : Stream.of(new Range(range.min(), candidate1));
            })
        .toList();
  }

  List<Range> buildNextRanges(List<Range> ranges, int candidate) {
    if (ranges.isEmpty()) {
      return List.of(new Range(candidate, candidate));
    }

    return ranges.stream().map(range -> combine(range, candidate)).toList();
  }

  Range combine(Range range, int value) {
    return new Range(Math.min(range.min(), value), Math.max(range.max(), value));
  }

  Outcome merge(Outcome o1, Outcome o2) {
    return (o1.operationNum() < o2.operationNum()
            || (o1.operationNum() == o2.operationNum() && o1.spread() <= o2.spread()))
        ? o1
        : o2;
  }
}

record Outcome(int operationNum, int spread) {}

record Range(int min, int max) {}
