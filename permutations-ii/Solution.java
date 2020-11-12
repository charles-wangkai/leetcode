import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<List<Integer>> permuteUnique(int[] num) {
    List<List<Integer>> permutations = new ArrayList<>();
    search(permutations, num, 0);

    return permutations;
  }

  void search(List<List<Integer>> permutations, int[] num, int index) {
    if (index == num.length) {
      permutations.add(
          IntStream.range(0, num.length).map(i -> num[i]).boxed().collect(Collectors.toList()));

      return;
    }

    Set<Integer> used = new HashSet<>();
    for (int i = index; i < num.length; ++i) {
      if (used.contains(num[i])) {
        continue;
      }
      used.add(num[i]);

      swap(num, index, i);
      search(permutations, num, index + 1);
      swap(num, index, i);
    }
  }

  void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }
}
