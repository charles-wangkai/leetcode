class Solution {
  public int kthGrammar(int n, int k) {
    return search(k);
  }

  int search(int k) {
    if (k == 1) {
      return 0;
    }

    if (search((k - 1) / 2 + 1) == 0) {
      return (k % 2 == 1) ? 0 : 1;
    }

    return (k % 2 == 1) ? 1 : 0;
  }
}
