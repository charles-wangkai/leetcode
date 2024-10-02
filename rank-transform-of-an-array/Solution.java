import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int[] arrayRankTransform(int[] arr) {
    int[] sortedIndices =
        IntStream.range(0, arr.length)
            .boxed()
            .sorted(Comparator.comparing(i -> arr[i]))
            .mapToInt(Integer::intValue)
            .toArray();

    int[] result = new int[arr.length];
    for (int i = 0; i < sortedIndices.length; ++i) {
      result[sortedIndices[i]] =
          (i == 0)
              ? 1
              : (result[sortedIndices[i - 1]]
                  + ((arr[sortedIndices[i]] == arr[sortedIndices[i - 1]]) ? 0 : 1));
    }

    return result;
  }
}
