public class Solution {
  public int lastRemaining(int n) {
    return search(n, true);
  }

  int search(int n, boolean leftOrRight) {
    if (n == 1) {
      return 1;
    }

    return search(n / 2, !leftOrRight) * 2 - ((!leftOrRight && n % 2 == 0) ? 1 : 0);
  }
}
