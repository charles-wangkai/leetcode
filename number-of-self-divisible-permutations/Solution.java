import java.util.stream.IntStream;

class Solution {
  public int selfDivisiblePermutationCount(int n) {
    return search(IntStream.rangeClosed(1, n).toArray(), 0);
  }

  int search(int[] permutation, int index) {
    if (index == permutation.length) {
      return 1;
    }

    int result = 0;
    for (int i = index; i < permutation.length; ++i) {
      swap(permutation, i, index);

      if (gcd(permutation[index], index + 1) == 1) {
        result += search(permutation, index + 1);
      }

      swap(permutation, i, index);
    }

    return result;
  }

  void swap(int[] permutation, int index1, int index2) {
    int temp = permutation[index1];
    permutation[index1] = permutation[index2];
    permutation[index2] = temp;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}