import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long makeSimilar(int[] nums, int[] target) {
    int[] evenNums = Arrays.stream(nums).filter(x -> x % 2 == 0).sorted().toArray();
    int[] oddNums = Arrays.stream(nums).filter(x -> x % 2 != 0).sorted().toArray();
    int[] evenTarget = Arrays.stream(target).filter(x -> x % 2 == 0).sorted().toArray();
    int[] oddTarget = Arrays.stream(target).filter(x -> x % 2 != 0).sorted().toArray();

    return (IntStream.range(0, evenNums.length)
                .map(i -> Math.abs(evenNums[i] - evenTarget[i]))
                .asLongStream()
                .sum()
            + IntStream.range(0, oddNums.length)
                .map(i -> Math.abs(oddNums[i] - oddTarget[i]))
                .asLongStream()
                .sum())
        / 4;
  }
}
