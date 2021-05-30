import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  public int maximumGap(int[] nums) {
    if (nums.length == 1) {
      return 0;
    }

    int[] sorted = sort(nums);

    return IntStream.range(0, sorted.length - 1)
        .map(i -> sorted[i + 1] - sorted[i])
        .max()
        .getAsInt();
  }

  int[] sort(int[] nums) {
    int[] sorted = nums;
    for (int i = 0; i < 32; ++i) {
      List<Integer> numbersWithZero = new ArrayList<>();
      List<Integer> numbersWithOne = new ArrayList<>();
      for (int num : sorted) {
        if ((num & (1 << i)) == 0) {
          numbersWithZero.add(num);
        } else {
          numbersWithOne.add(num);
        }
      }

      sorted =
          Stream.concat(numbersWithZero.stream(), numbersWithOne.stream())
              .mapToInt(x -> x)
              .toArray();
    }

    return sorted;
  }
}
