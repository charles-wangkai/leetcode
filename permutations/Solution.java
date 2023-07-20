import java.util.ArrayList;
import java.util.List;

public class Solution {
  public List<List<Integer>> permute(int[] num) {
    List<List<Integer>> result = new ArrayList<>();
    search(result, num, 0);
    return result;
  }

  void search(List<List<Integer>> result, int[] num, int index) {
    if (index == num.length) {
      List<Integer> permutation = new ArrayList<>();
      for (int oneNum : num) {
        permutation.add(oneNum);
      }
      result.add(permutation);
      return;
    }
    for (int i = index; i < num.length; i++) {
      swap(num, index, i);
      search(result, num, index + 1);
      swap(num, index, i);
    }
  }

  void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }
}
