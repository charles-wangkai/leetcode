class Solution {
  public int reinitializePermutation(int n) {
    int result = 0;
    int index = 1;
    while (index != 1 || result == 0) {
      if (index % 2 == 0) {
        index /= 2;
      } else {
        index = n / 2 + index / 2;
      }

      ++result;
    }

    return result;
  }
}
