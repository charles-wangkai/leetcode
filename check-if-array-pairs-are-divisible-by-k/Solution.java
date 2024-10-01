import java.util.stream.IntStream;

class Solution {
  public boolean canArrange(int[] arr, int k) {
    int[] remainderCounts = new int[k];
    for (int x : arr) {
      ++remainderCounts[Math.floorMod(x, k)];
    }

    return IntStream.range(0, k)
        .allMatch(
            i -> {
              int other = (k - i) % k;

              return (other == i)
                  ? (remainderCounts[i] % 2 == 0)
                  : (remainderCounts[i] == remainderCounts[other]);
            });
  }
}