import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int[][] permute(int n) {
    List<int[]> permutations = new ArrayList<>();
    search(permutations, IntStream.rangeClosed(1, n).toArray(), 0);

    return permutations.stream()
        .sorted(
            (p1, p2) ->
                IntStream.range(0, p1.length)
                    .map(i -> Integer.compare(p1[i], p2[i]))
                    .filter(cmp -> cmp != 0)
                    .findFirst()
                    .getAsInt())
        .toArray(int[][]::new);
  }

  void search(List<int[]> permutations, int[] permutation, int index) {
    if (index == permutation.length) {
      permutations.add(permutation.clone());

      return;
    }

    for (int i = index; i < permutation.length; ++i) {
      if (index == 0 || permutation[i] % 2 != permutation[index - 1] % 2) {
        swap(permutation, i, index);
        search(permutations, permutation, index + 1);
        swap(permutation, i, index);
      }
    }
  }

  void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }
}