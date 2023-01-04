import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int kBigIndices(int[] nums, int k) {
    boolean[] leftValids = buildLeftValids(nums, k);
    boolean[] rightValids = buildRightValids(nums, k);

    return (int)
        IntStream.range(0, nums.length).filter(i -> leftValids[i] && rightValids[i]).count();
  }

  boolean[] buildRightValids(int[] nums, int k) {
    int A[] = new int[Integer.highestOneBit(Arrays.stream(nums).max().getAsInt()) * 2 + 1];

    boolean[] result = new boolean[nums.length];
    for (int i = result.length - 1; i >= 0; --i) {
      result[i] = prefix_sum(A, nums[i] - 1) >= k;
      add(A, nums[i], 1);
    }

    return result;
  }

  boolean[] buildLeftValids(int[] nums, int k) {
    int A[] = new int[Integer.highestOneBit(Arrays.stream(nums).max().getAsInt()) * 2 + 1];

    boolean[] result = new boolean[nums.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] = prefix_sum(A, nums[i] - 1) >= k;
      add(A, nums[i], 1);
    }

    return result;
  }

  int LSB(int i) {
    return i & -i;
  }

  int prefix_sum(int[] A, int i) {
    int sum = A[0];
    for (; i != 0; i -= LSB(i)) sum += A[i];
    return sum;
  }

  void add(int[] A, int i, int delta) {
    if (i == 0) {
      A[0] += delta;
      return;
    }
    for (; i < A.length; i += LSB(i)) A[i] += delta;
  }
}
